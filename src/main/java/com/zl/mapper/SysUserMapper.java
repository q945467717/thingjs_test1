package com.zl.mapper;

import com.zl.model.SysRole;
import com.zl.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysUserMapper {

    @Insert("insert into sys_user(username,password) values(#{username},#{password})")
    void save(String username,String password);

    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);

    @Select("SELECT * FROM sys_user_roles LEFT JOIN sys_role ON sys_user_roles.roles_id=sys_role.id where sys_user_id=#{id}")
    List<SysRole> sysRoles(String id);

    @Select("select * from sys_user su left join sys_user_roles sur on su.id = sur.sys_user_id left join sys_role sr on sur.roles_id =sr.id where sr.id=3")
    List<SysUser> allsysUser();

    @Select("select * from sys_user where id = #{id}")
    SysUser findById(Integer id);

    @Update("update sys_user set sadd=#{sadd} where id=#{id}")
    void setAdd(String sadd, Integer id);

    @Update("update sys_user set sdelete=#{sdelete} where id=#{id}")
    void setDelete(String sdelete, Integer id);

    @Update("update sys_user set supdate=#{supdate} where id=#{id}")
    void setUpdate(String supdate, Integer id);


}
