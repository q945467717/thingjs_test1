package com.zl.service;

import com.zl.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    void save(String username,String password);

    List allsysUser();

    void setAdd(Integer id);

    void setDelete(Integer id);

    void setUpdate(Integer id);


}
