package com.zl.service.impl;

import com.zl.mapper.StationMapper;
import com.zl.mapper.SysUserMapper;
import com.zl.model.Station;
import com.zl.model.SysUser;
import com.zl.model.vo.SysUserStation;
import com.zl.service.StationService;
import com.zl.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private StationMapper stationMapper;


    /**
     * 新增管理员
     * @param stationName 管理站点名称
     * @param username  用户名
     * @param password  密码
     */
    @Override
    @Transactional
    public void save(String[] stationName,String username, String password) {

        List<Integer> stationList = new ArrayList<>();

        for(String name :stationName){
            Station station = stationMapper.oneStationByName(name);
            stationList.add(station.getStationId());
        }

        SysUser sysUser = new SysUser();
        //sysUser.setStationId(station.getStationId());
        sysUser.setUsername(username);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));

        int userId = sysUserMapper.save(sysUser);

        sysUserMapper.addRoles(sysUser.getId(),3);

        for(int stationId:stationList){
            sysUserMapper.addStation(sysUser.getId(),stationId);
        }
    }

    /**
     * 展示管理员用户
     * @return 用户列表
     */
    @Override
    @Transactional
    public List allsysUser() {

        List<SysUser> sysUsers = sysUserMapper.allsysUser();

        List<Map> resultList = new ArrayList<Map>();


        for(SysUser sysUser:sysUsers){

            List<String> stationName = new ArrayList<>();

            sysUser.setRoles(sysUserMapper.sysRoles(3));

            List<SysUserStation> sysUserStations = stationMapper.oneSysUserStation(sysUser.getId());
            for(SysUserStation sysUserStation:sysUserStations){
                Station station = stationMapper.oneStation(sysUserStation.getStation_id());
                stationName.add(station.getStationName());
            }

            String add =String.valueOf(sysUser.getSadd());
            String delete =String.valueOf(sysUser.getSdelete());
            String update =String.valueOf(sysUser.getSupdate());
            String stationUpdate =String.valueOf(sysUser.getSsupdate());
            String id = String.valueOf(sysUser.getId());

            Map resultMap = new HashMap();

            resultMap.put("username",sysUser.getUsername());
            resultMap.put("sadd",add);
            resultMap.put("sdelete",delete);
            resultMap.put("supdate",update);
            resultMap.put("ssupdate",stationUpdate);
            resultMap.put("id",id);
            resultMap.put("stationName",stationName);
            resultList.add(resultMap);
        }

        return resultList;
    }
    //修改权限 增加
    @Override
    public void setAdd(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);
        if(sysUser.getSadd().equals("1")){
            sysUserMapper.setAdd("0",id);
        }else {
            sysUserMapper.setAdd("1",id);
        }
    }
    //修改权限 删除
    @Override
    public void setDelete(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSdelete().equals("1")){
            sysUserMapper.setDelete("0",id);
        }else {
            sysUserMapper.setDelete("1",id);
        }
    }
    //修改权限 更新物体
    @Override
    public void setUpdate(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSupdate().equals("1")){
            sysUserMapper.setUpdate("0",id);
        }else {
            sysUserMapper.setUpdate("1",id);
        }
    }
    //修改权限 更新站点
    @Override
    public void setStationUpdate(Integer id) {
        SysUser sysUser = sysUserMapper.findById(id);

        if(sysUser.getSsupdate().equals("1")){
            sysUserMapper.setStationUpdate("0",id);
        }else {
            sysUserMapper.setStationUpdate("1",id);
        }
    }
    //检查用户名是否重复
    @Override
    public SysUser logincheck(String username) throws UsernameNotFoundException {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteAdmin(Integer userId) {
        sysUserMapper.deleteAdmin(userId);

        sysUserMapper.deleteStationRoles(userId);

        sysUserMapper.deleteUserRoles(userId);
    }

    //修改密码
    @Override
    public void changePassword(String password) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SysUser principal =(SysUser) authentication.getPrincipal();

        String newPassword = bCryptPasswordEncoder.encode(password);

        sysUserMapper.changePassword(principal.getUsername(),newPassword);

    }

    @Override
    @Transactional
    public void updateAdmin(String[] stationName, Integer userId) {

        sysUserMapper.deleteStationRoles(userId);

        for(String name:stationName){
            Station station = stationMapper.oneStationByName(name);
            sysUserMapper.addStation(userId,station.getStationId());
        }

    }

}
