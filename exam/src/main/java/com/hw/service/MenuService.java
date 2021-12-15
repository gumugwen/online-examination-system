package com.hw.service;

import com.hw.common.Constant;
import com.hw.mapper.MenuMapper;
import com.hw.model.Menu;
import com.hw.model.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:04 下午
 */
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> menuList(Integer role){
        List<Menu> menuList = menuMapper.findByRoleId(role);
        return menuList;
    }
    public MenuVO findByRoleId(Integer role) {
        Menu parent = menuMapper.selectParentMenu(role);
        List<Menu> menuList = menuMapper.findByRoleIdAndPid(role,parent.getId());
        List<MenuVO.MenuInfo>menuInfos=new ArrayList<>();
        MenuVO.MenuInfo info=null;
        for (Menu menu : menuList) {
            info=new MenuVO.MenuInfo();
            info.setTarget("_self");
            info.setIcon(menu.getIcon());
            info.setTitle(menu.getName());
            info.setHref(menu.getUrl());
            menuInfos.add(info);
        }
        MenuVO vo = new MenuVO();
        MenuVO.MenuInfo menuInfo = new MenuVO.MenuInfo();
        MenuVO.HomeInfo homeInfo = new MenuVO.HomeInfo();
        if(role.equals(Constant.STUDENT)){
            homeInfo.setTitle("首页");
            homeInfo.setHref("test/toStudentIndex");
        }else if(role.equals(Constant.ADMIN)){
            homeInfo.setTitle("首页");
            homeInfo.setHref("test/toIndex");
        }else{
            homeInfo.setTitle("首页");
            homeInfo.setHref("test/toIndex");
        }
        menuInfo.setTitle(parent.getName());
        menuInfo.setIcon(parent.getIcon());
        menuInfo.setTarget("_self");
        menuInfo.setChild(menuInfos);
        vo.setMenuInfo(new ArrayList<MenuVO.MenuInfo>(){{add(menuInfo);}});
        vo.setHomeInfo(homeInfo);
        return vo;
    }


}
