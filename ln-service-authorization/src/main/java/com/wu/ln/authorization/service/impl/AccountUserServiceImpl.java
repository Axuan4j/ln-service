package com.wu.ln.authorization.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wu.ln.authorization.dao.UserMapper;
import com.wu.ln.authorization.entity.UserVO;
import com.wu.ln.authorization.service.AccountUserService;
import com.wu.ln.db.UserAccountDB;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountUserServiceImpl extends ServiceImpl<UserMapper, UserAccountDB> implements AccountUserService {

    private final PasswordEncoder passwordEncoder;

    public AccountUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDB getUserByUsername(String username) {
        LambdaQueryWrapper<UserAccountDB> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccountDB::getUserName, username);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean checkUsername(String username) {
        LambdaQueryWrapper<UserAccountDB> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccountDB::getUserName, username);
        queryWrapper.last("limit 1");
        return this.count(queryWrapper) > 0;
    }

    @Override
    public Integer register(UserVO userVO) {
        UserAccountDB userAccountDB = new UserAccountDB();
        userAccountDB.setUserName(userVO.getUsername());
        String safeCode = RandomUtil.randomString(6);
        userAccountDB.setSafeCode(safeCode);
        String hexPassword = DigestUtil.md5Hex(userVO.getPassword() + safeCode);
        userAccountDB.setPassword(passwordEncoder.encode(hexPassword));
        userAccountDB.setStatus(0);
        userAccountDB.setIsVip(0);
        userAccountDB.setEmail(userVO.getEmail());
        userAccountDB.setPhone(userVO.getPhone());
        userAccountDB.setCreateTime(DateUtil.now());
        return this.baseMapper.insert(userAccountDB);
    }

    @Override
    public UserAccountDB loadUserByEmail(String email) {
        LambdaQueryWrapper<UserAccountDB> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccountDB::getEmail, email);
        return this.getOne(queryWrapper);
    }
}
