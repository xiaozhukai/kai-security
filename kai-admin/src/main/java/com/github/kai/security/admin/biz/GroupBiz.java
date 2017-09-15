package com.github.kai.security.admin.biz;

import com.github.kai.security.admin.constant.CommonConstant;
import com.github.kai.security.admin.entity.Group;
import com.github.kai.security.admin.entity.Menu;
import com.github.kai.security.admin.entity.ResourceAuthority;
import com.github.kai.security.admin.mapper.GroupMapper;
import com.github.kai.security.admin.mapper.MenuMapper;
import com.github.kai.security.admin.mapper.ResourceAuthorityMapper;
import com.github.kai.security.admin.mapper.UserMapper;
import com.github.kai.security.admin.vo.AuthorityMenuTree;
import com.github.kai.security.admin.vo.GroupUsers;
import com.github.kai.security.common.biz.BaseBiz;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:  team业务层
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 23:10
 */
@Service
public class GroupBiz extends BaseBiz<GroupMapper, Group> {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void insertSelective(Group entity) {
        //判断是否是父节点
        if (CommonConstant.ROOT == entity.getParentId()) {
            entity.setPath("/" + entity.getCode());
        } else {
            //通过父类节点获取这个team
            Group parent = this.selectById(entity.getParentId());
            // 设置team路径
            entity.setPath(parent.getPath() + "/" + entity.getCode());
        }
        //插入这个team
        super.insertSelective(entity);
    }

    /**
     TODO: 更具team的groupId进行更新
     Author: kai
     CreateDate: 2017/9/6
     CreateTime: 19:09
     */
    @Override
    public void updateById(Group entity) {
        if (CommonConstant.ROOT == entity.getParentId()) {
            entity.setPath("/" + entity.getCode());
        } else {
            Group parent = this.selectById(entity.getParentId());
            entity.setPath(parent.getPath() + "/" + entity.getCode());
        }
        //根据id更行team
        super.updateById(entity);
    }

    /**
     TODO: 获取群组关联用户
     Author: kai
     CreateDate: 2017/9/6
     CreateTime: 21:33
     */
    public GroupUsers getGroupUsers(int groupId) {
        return new GroupUsers(userMapper.selectMemberByGroupId(groupId), userMapper.selectLeaderByGroupId(groupId));
    }

    /**
     TODO: 变更群主所分配用户
     Author: kai
     CreateDate: 2017/9/6
     CreateTime: 22:01
     */
    public void modifyGroupUsers(int groupId, String members, String leaders) {
        //删除team的groupId分组
        mapper.deleteGroupLeadersById(groupId);
        mapper.deleteGroupMembersById(groupId);

        //判断队员不为空
        if (!StringUtils.isEmpty(members)) {
            String[] mem = members.split(",");
            for (String m : mem) {
                //插入队员信息到team
                mapper.insertGroupMembersById(groupId, Integer.parseInt(m));
            }
        }
        //判断领导者不为空
        if (!StringUtils.isEmpty(leaders)) {
            String[] mem = leaders.split(",");
            for (String m : mem) {
                //插入领导者信息到team
                mapper.insertGroupLeadersById(groupId, Integer.parseInt(m));
            }
        }
    }

    /**
     TODO: 变更群组关联的菜单
     Author: kai
     CreateDate: 2017/9/6
     CreateTime: 22:10
     */
    public void modifyAuthorityMenu(int groupId, List<AuthorityMenuTree> menuTrees) {
        //删除节点菜单
        resourceAuthorityMapper.deleteByAuthorityIdAndResourceType(groupId + "", CommonConstant.RESOURCE_TYPE_MENU);

        ResourceAuthority authority = null;
        for (AuthorityMenuTree menuTree : menuTrees) {
            //资源授权
            authority = new ResourceAuthority(CommonConstant.AUTHORITY_TYPE_GROUP, CommonConstant.RESOURCE_TYPE_MENU);
            authority.setAuthorityId(groupId + "");
            authority.setResourceId(menuTree.getId() + "");
            authority.setParentId("-1");
            resourceAuthorityMapper.insertSelective(authority);
        }
    }

    /**
     TODO: 分配资源权限
     Author: kai
     CreateDate: 2017/9/6
     CreateTime: 22:26
     */
    public void modifyAuthorityElement(int groupId, int menuId, int elementId) {
        ResourceAuthority authority = new ResourceAuthority(CommonConstant.AUTHORITY_TYPE_GROUP, CommonConstant.RESOURCE_TYPE_BTN);
        authority.setAuthorityId(groupId + "");
        authority.setResourceId(elementId + "");
        authority.setParentId("-1");
        resourceAuthorityMapper.insertSelective(authority);
    }

    /**
     * TODO: 移除资源权限
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:27
     */
    public void removeAuthorityElement(int groupId, int menuId, int elementId) {
        ResourceAuthority authority = new ResourceAuthority();
        //设置资源授权位置
        authority.setAuthorityId(groupId+"");
        authority.setResourceId(elementId+"");
        authority.setParentId("-1");
        //删除资源权限
        resourceAuthorityMapper.delete(authority);
    }

    /**
     * TODO: 获取群主关联的菜单
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 22:31
     */
    public List<AuthorityMenuTree> getAuthorityMenu(int groupId){
        //获取菜单集合
        List<Menu> menus = menuMapper.selectMenuByAuthorityId(String.valueOf(groupId), CommonConstant.AUTHORITY_TYPE_GROUP);
        //获取授权菜单树
        List<AuthorityMenuTree> trees = new ArrayList<AuthorityMenuTree>();
        AuthorityMenuTree node = null;
        for (Menu menu : menus) {
            node = new AuthorityMenuTree();
            //设置节点标题
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            //授权树添加节点
            trees.add(node);
        }
        return trees;
    }

    public List<Integer> getAuthorityElement(int groupId) {
        //设置team资源按钮
        ResourceAuthority authority = new ResourceAuthority(CommonConstant.AUTHORITY_TYPE_GROUP,CommonConstant.RESOURCE_TYPE_BTN);

        authority.setAuthorityId(groupId+"");
        //设置授权资源集合
        List<ResourceAuthority> authorities = resourceAuthorityMapper.select(authority);
        List<Integer> ids = new ArrayList<Integer>();
        //循环添加资源id
        for(ResourceAuthority auth:authorities){
            ids.add(Integer.parseInt(auth.getResourceId()));
        }
        return ids;
    }

}
