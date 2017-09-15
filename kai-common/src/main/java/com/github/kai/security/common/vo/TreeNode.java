package com.github.kai.security.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 树形节点vo类
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 23:12
 */
public class TreeNode {
    protected int id;
    protected int parentId;

    /**
     * TODO: 获得自查节点集合
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:15
     */
    public List<TreeNode> getChildren(){
        return children;
    }

    /**
     * TODO: 设置自查节点集合
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:16
     */
    public void setChildren(List<TreeNode> children){
        this.children = children;
    }

    /**
     * TODO: 集合接收节点树
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:18
     */
    List<TreeNode> children =  new ArrayList<TreeNode>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * TODO: 添加节点 --> children集合中
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:19
     */
    public void add(TreeNode node){
        children.add(node);
    }
}
