package com.github.kai.security.admin.mapper;

import com.github.kai.security.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * TODO: 用户mapper
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 18:41
 */
public interface UserMapper extends Mapper<User>{

    /**
     * TODO: 根据team的groupId查询队员
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:44
     */
    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);

    /**
     * TODO: 根据team的groupId查询领导者
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:45
     */
    public List<User> selectLeaderByGroupId(@Param("groupId") int groupId);
}
