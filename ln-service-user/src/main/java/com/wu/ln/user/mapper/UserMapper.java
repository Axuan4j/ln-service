package com.wu.ln.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.ln.db.UserAccountDB;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserAccountDB> {

}
