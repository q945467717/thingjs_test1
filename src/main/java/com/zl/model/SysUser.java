package com.zl.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SysUser implements UserDetails {

    private int id;
    private String username;
    private String password;
    private String sadd;
    private String sdelete;
    private String supdate;
    private String ssupdate;

    public String getSsupdate() {
        return ssupdate;
    }

    public void setSsupdate(String ssupdate) {
        this.ssupdate = ssupdate;
    }

    public String getSadd() {
        return sadd;
    }

    public void setSadd(String sadd) {
        this.sadd = sadd;
    }

    public String getSdelete() {
        return sdelete;
    }

    public void setSdelete(String sdelete) {
        this.sdelete = sdelete;
    }

    public String getSupdate() {
        return supdate;
    }

    public void setSupdate(String supdate) {
        this.supdate = supdate;
    }

    private List<SysRole> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sadd='" + sadd + '\'' +
                ", sdelete='" + sdelete + '\'' +
                ", supdate='" + supdate + '\'' +
                ", ssupdate='" + ssupdate + '\'' +
                ", roles=" + roles +
                '}';
    }
}
