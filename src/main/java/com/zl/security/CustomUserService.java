package com.zl.security;

import com.zl.mapper.SysUserMapper;
import com.zl.mapper.UserMapper;
import com.zl.model.SysRole;
import com.zl.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUser user = sysUserMapper.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //System.out.println("s:"+s);
        //System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        //System.out.println(user.getId());

        List<SysRole> list = sysUserMapper.sysRoles(user.getId());
        user.setRoles(list);
//        List<SysRole> list = new ArrayList<SysRole>();
//        SysRole sysRole = new SysRole();
//        sysRole.setName("ROLE_ADMIN");
//        list.add(sysRole);
//        user.setRoles(list);

        return user;
    }

}
