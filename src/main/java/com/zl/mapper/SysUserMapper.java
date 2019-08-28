package com.zl.mapper;

import com.zl.model.SysRole;
import com.zl.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper {

    @Insert("insert into sys_user(id,username,password) values(#{id},#{username},#{password})")
    void save(String username,String password);

    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);

    @Select("SELECT * FROM sys_user_roles LEFT JOIN sys_role ON sys_user_roles.roles_id=sys_role.id where sys_user_id=#{id}")
    List<SysRole> sysRoles(String id);
}
