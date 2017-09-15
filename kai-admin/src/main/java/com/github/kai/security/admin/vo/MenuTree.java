package com.github.kai.security.admin.vo;

import com.github.kai.security.common.vo.TreeNode;
import org.aspectj.apache.bcel.classfile.ConstantNameAndType;

/**
 * TODO: 菜单树
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 19:31
 */
public class MenuTree extends TreeNode{
    String icon;
    String title;
    String href;
    boolean spread = false;

    public MenuTree() {
    }

    /**
     * TODO: 根据父节点id设置tree
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 19:34
     */
    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
    }

    /**
     * TODO: 根据菜单获取父节点id设置
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 19:34
     */
    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
}
