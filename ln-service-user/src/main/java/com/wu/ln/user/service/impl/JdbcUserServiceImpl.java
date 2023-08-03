package com.wu.ln.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wu.ln.db.UserAccountDB;
import com.wu.ln.util.Constant;
import com.wu.ln.user.entity.UserServiceDetail;
import com.wu.ln.user.mapper.UserMapper;
import com.wu.ln.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserServiceImpl extends ServiceImpl<UserMapper, UserAccountDB> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(JdbcUserServiceImpl.class);

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserServiceDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserAccountDB> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserAccountDB::getUserName, username);
        wrapper.last("limit 1");
        UserAccountDB userAccountDB = this.baseMapper.selectOne(wrapper);
        if (userAccountDB == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserServiceDetail userServiceDetail = new UserServiceDetail();
        userServiceDetail.setUsername(userAccountDB.getUserName());
        userServiceDetail.setPassword(userAccountDB.getPassword());
        userServiceDetail.setSafeCode(userAccountDB.getSafeCode());
        userServiceDetail.setLocked(getUserLocked(username));
        userServiceDetail.setStatus(userAccountDB.getStatus());

        return userServiceDetail;
    }

    private boolean getUserLocked(String username) {
        try {
            String value = redisTemplate.opsForValue().get(Constant.LOCKED_REDIS_KEY + username);
            if (StrUtil.isNotBlank(value)) {
                return Integer.parseInt(value) > Constant.ACCOUNT_LOGIN_FAIL_MAX_COUNT;
            }
        } catch (Exception e) {
            logger.error("获取用户锁定状态失败:" + e.getMessage(), e);
        }
        return false;
    }
}
