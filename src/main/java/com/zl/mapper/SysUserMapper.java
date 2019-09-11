package com.zl.mapper;

import com.zl.model.SysRole;
import com.zl.model.SysUser;
import com.zl.model.vo.SysUserStation;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {

    //@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    //@Insert("insert into sys_user(username,password,stationId) values(#{username},#{password},#{stationId})")
    int save(SysUser sysUser);

    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);

    @Select("SELECT * FROM sys_user_roles LEFT JOIN sys_role ON sys_user_roles.roles_id=sys_role.id where sys_user_id=#{id}")
    List<SysRole> sysRoles(int id);

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

    @Update("update sys_user set ssupdate=#{ssupdate} where id=#{id}")
    void setStationUpdate(String ssupdate, Integer id);

    @Insert("insert into sys_user_roles(sys_user_id,roles_id) values(#{userId},#{roleId})")
    void addRoles(int userId,Integer roleId);

    @Insert("insert into user_station(user_id,station_id) values(#{userId},#{stationId})")
    void addStation(int userId,int stationId);

    @Select("select * from user_station where user_id=#{userId}")
    List<SysUserStation> adminStations(int userId);

    @Delete("delete from user_station where user_id=#{userId} and station_id=#{stationId}")
    void deleteStationRole(int userId,Integer stationId);

    @Delete("delete from sys_user where id=#{userId}")
    void deleteAdmin(Integer userId);

    @Delete("delete from user_station where user_id=#{userId}")
    void deleteStationRoles(Integer userId);

    @Delete("delete from sys_user_roles where sys_user_id=#{userId}")
    void deleteUserRoles(Integer userId);

}
