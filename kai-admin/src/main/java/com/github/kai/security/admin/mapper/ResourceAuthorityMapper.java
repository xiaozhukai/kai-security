package com.github.kai.security.admin.mapper;

import com.github.kai.security.admin.entity.ResourceAuthority;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * TODO:
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 18:37
 */
public interface ResourceAuthorityMapper extends Mapper<ResourceAuthority> {
    /**
     * TODO: 通过授权id和资源类型删除授权资源
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:39
     */
    public void deleteByAuthorityIdAndResourceType(@Param("authorityId") String authorityId, @Param("resourceType") String resourceType);

}
