package com.zl.service.impl;

import com.zl.mapper.SysUserMapper;
import com.zl.model.SysUser;
import com.zl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void save(String username, String password) {
        sysUserMapper.save(username,password);
    }

    @Override
    public List allsysUser() {

        List<SysUser> list = sysUserMapper.allsysUser();

        List<Map> list1 = new ArrayList<Map>();

        for(SysUser sysUser:list){

            sysUser.setRoles(sysUserMapper.sysRoles("3"));
            String add =String.valueOf(sysUser.getSadd());
            String delete =String.valueOf(sysUser.getSdelete());
            String update =String.valueOf(sysUser.getSupdate());
            String id = String.valueOf(sysUser.getId());

            Map<String,String> map = new HashMap<>();

            map.put("username",sysUser.getUsername());
            map.put("sadd",add);
            map.put("sdelete",delete);
            map.put("supdate",update);
            map.put("id",id);
            list1.add(map);
        }

        return list1;
    }

    @Override
    public void setAdd(Integer id) {

        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSadd().equals("1")){
            sysUserMapper.setAdd("0",id);
        }else {
            sysUserMapper.setAdd("1",id);
        }


    }

    @Override
    public void setDelete(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSdelete().equals("1")){
            sysUserMapper.setDelete("0",id);
        }else {
            sysUserMapper.setDelete("1",id);
        }
    }

    @Override
    public void setUpdate(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSupdate().equals("1")){
            sysUserMapper.setUpdate("0",id);
        }else {
            sysUserMapper.setUpdate("1",id);
        }
    }

}
