package com.wu.ln.authorization.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.ln.db.UserAccountDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserAccountDB> {

    UserAccountDB getUserByUsername(@Param("username") String username);
}
