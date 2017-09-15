package com.github.kai.security.admin.mapper;

import com.github.kai.security.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * TODO:
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 18:32
 */
public interface MenuMapper extends Mapper<Menu> {

    /**
     * TODO: 通过授权id和授权类型查询菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:33
     */
    public List<Menu> selectMenuByAuthorityId(@Param("authorityId") String authorityId, @Param("authorityType") String authorityType);

    /**
     * TODO: 根据用户和组的权限关系查找用户可访问菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:35
     */
    public List<Menu> selectAuthorityMenuByUserId(@Param("userId") int userId);

    /**
     * TODO: 根据用户和组的权限关系查找用户可访问的系统
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:36
     */
    public List<Menu> selectAuthoritySystemByUserId(@Param("userId") int userId);

}
