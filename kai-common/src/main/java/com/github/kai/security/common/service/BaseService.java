package com.github.kai.security.common.service;

import java.util.List;

/**
 * TODO: 父类业务层接口
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 22:29
 */
public interface BaseService<T> {

    /**
     * TODO: 查询单条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:31
     */
    T selectOne(T entity);

    /**
     * TODO: 通过id查询到对应数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:32
     */
    T selectById(Object id);

    /**
     * TODO: 根据ID集合来查询对应的数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:33
     */
//    List<T> selectListByIds(List<Object> ids);

    /**
     * TODO: 查询整个列表
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:34
     */
    List<T> selectList(T entity);

    /**
     * TODO: 查询所有的集合数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:35
     */
    List<T> selectListAll();

    /**
     * TODO: 统计所有的条数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:36
     */
//    Long selectCountAll();

    /**
     * TODO: 查询总记录数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:37
     */
    Long selectCount(T entity);

    /**
     * TODO: 添加
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:38
     */
    void insert(T entity);

    /**
     * TODO: 添加不插入null字段
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:39
     */
    void insertSelective(T entity);

    /**
     * TODO: 删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:08
     */
    void delete(T entity);

    /**
     * TODO: 根据id删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:40
     */
    void deleteById(Object id);

    /**
     * TODO: 根据id更新
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:40
     */
    void updateById(T entity);

    /**
     * TODO:  更新不插入null字段
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:41
     */
    void updateSelectiveById(T entity);

    /**
     * TODO: 根据id集合批量删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:43
     */
//    void deleteBatchByIds(List<Object> ids);

    /**
     * TODO: 批量更新
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:54
     */
//    void  updateBatch(List<T> entitys);
}
