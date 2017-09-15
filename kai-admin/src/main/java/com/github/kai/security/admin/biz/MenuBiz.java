package com.github.kai.security.admin.biz;

import com.github.kai.security.admin.constant.CommonConstant;
import com.github.kai.security.admin.entity.Menu;
import com.github.kai.security.admin.mapper.MenuMapper;
import com.github.kai.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: 菜单栏业务层
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 22:44
 */
@Service
public class MenuBiz extends BaseBiz<MenuMapper,Menu> {

    /**
     * TODO: 添加菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:46
     */
    @Override
    public void insertSelective(Menu entity) {
        //判断是否是父节点
        if (CommonConstant.ROOT == entity.getParentId()){
            //设置父节点路径
            entity.setPath("/" + entity.getCode());
        }else{
            //获取到父节点菜单
            Menu parent = this.selectById(entity.getParentId());
            //设置父节点菜单路径
            entity.setPath(parent.getPath()+"/"+entity.getCode());
        }
        super.insertSelective(entity);
    }

    /**
     * TODO: 根据节点更新菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:46
     */
    @Override
    public void updateById(Menu entity) {
        //判断是否是父节点
        if(CommonConstant.ROOT == entity.getParentId()){
            //设置父节点路径
            entity.setPath("/"+entity.getCode());
        }else{
            //获取到父节点菜单
            Menu parent = this.selectById(entity.getParentId());
            //设置父节点菜单路径
            entity.setPath(parent.getPath()+"/"+entity.getCode());
        }
        //根据id更新
        super.updateById(entity);
    }

    /**
     * TODO: 获取用户可以访问授权菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:50
     */
    public List<Menu> getUserAuthorityMenuByUserId(int id){
        return mapper.selectAuthorityMenuByUserId(id);
    }

    /**
     * TODO: 根据用户id获取可以访问的系统
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:53
     */
    public List<Menu> getUserAuthoritySystemByUserId(int id){
        return mapper.selectAuthoritySystemByUserId(id);
    }

}
