package com.github.kai.security.admin.mapper;

import com.github.kai.security.admin.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * TODO: 节点mapper
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 18:04
 */
public interface ElementMapper extends Mapper<Element>{
    /**
     * TODO: 通过用户id查询授权节点集合
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:08
     */
    public List<Element> selectAuthorityElementByUserId(@Param("userId") String userId);

    /**
     * TODO: 通过用户id查询授权菜单集合
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:08
     */
    public List<Element> selectAuthorityMenuElementByUserId(@Param("userId") String userId, @Param("menuId") String menuId);

    /**
     * TODO: 通过用户id查询授权子节点
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:18
     */
    public List<Element> selectAuthorityElementByClientId(@Param("clientId") String clientId);
}
