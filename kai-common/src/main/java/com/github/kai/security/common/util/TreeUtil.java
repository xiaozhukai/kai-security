package com.github.kai.security.common.util;


import com.github.kai.security.common.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 树形结构工具
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 11:45
 */
public class TreeUtil{
  /**
   * TODO:  两层循环实现建树
   *
   * Author: kai
   * CreateDate: 2017/9/6
   * CreateTime: 11:45
   */
  public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {

    List<T> trees = new ArrayList<T>();

    for (T treeNode : treeNodes) {

      if (root.equals(treeNode.getParentId())) {
        trees.add(treeNode);
      }

      for (T it : treeNodes) {
        if (it.getParentId() == treeNode.getId()) {
          if (treeNode.getChildren() == null) {
            treeNode.setChildren(new ArrayList<TreeNode>());
          }
          treeNode.add(it);
        }
      }
    }
    return trees;
  }

  /**
   * TODO: 使用递归方法建树
   *
   * Author: kai
   * CreateDate: 2017/9/6
   * CreateTime: 11:45
   */
  public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes,Object root) {
    List<T> trees = new ArrayList<T>();
    for (T treeNode : treeNodes) {
      if (root.equals(treeNode.getParentId())) {
        trees.add(findChildren(treeNode, treeNodes));
      }
    }
    return trees;
  }

 /**
  * TODO: 递归查找子节点
  *
  * Author: kai
  * CreateDate: 2017/9/6
  * CreateTime: 11:45
  */
  public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
    for (T it : treeNodes) {
      if (treeNode.getId() == it.getParentId()) {
        if (treeNode.getChildren() == null) {
          treeNode.setChildren(new ArrayList<TreeNode>());
        }
        treeNode.add(findChildren(it, treeNodes));
      }
    }
    return treeNode;
  }

}
