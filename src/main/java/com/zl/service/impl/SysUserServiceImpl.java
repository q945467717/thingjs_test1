package com.zl.service.impl;

import com.zl.mapper.SysUserMapper;
import com.zl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void save(String username, String password) {
        sysUserMapper.save(username,password);
    }

}
