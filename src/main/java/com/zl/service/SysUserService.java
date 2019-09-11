package com.zl.service;

import com.zl.model.SysUser;

import java.util.List;


public interface SysUserService {

    void save(String[] stationName,String username,String password);

    List allsysUser();

    void setAdd(Integer id);

    void setDelete(Integer id);

    void setUpdate(Integer id);

    void setStationUpdate(Integer id);

    SysUser logincheck(String username);

    void deleteAdmin(Integer userId);

    void changePassword(String password);

    void updateAdmin(String[] stationName,Integer userId);


}
