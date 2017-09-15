package com.github.kai.security.admin.mapper;

import com.github.kai.security.admin.entity.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * TODO: 团队操作
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 18:21
 */
public interface GroupMapper extends Mapper<Group>{
    /**
     * TODO: 通过groupId删除team
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:23
     */
    public void deleteGroupMembersById(@Param("groupId") int groupId);

    /**
     * TODO: 通过team管理节点的groupId删除team
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:24
     */
    public void deleteGroupLeadersById(@Param("groupId") int groupId);

    /**
     * TODO: 通过team的groupId添加会员
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:26
     */
    public void insertGroupMembersById(@Param("groupId") int groupId, @Param("userId") int userId);

    /**
     * TODO: 通过team管理节点的groupId添加会员
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:27
     */
    public void insertGroupLeadersById(@Param("groupId") int groupId, @Param("userId") int userId);
}
