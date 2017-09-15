package com.github.kai.security.common.service.impl;

import com.github.kai.security.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * TODO: 父类业务层实现
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 22:29
 */
public class BaseServiceImpl<M extends Mapper<T>,T> implements BaseService<T>{

    @Autowired
    protected M mapper;

    /**
     * TODO: 查询单条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:56
     */
    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    /**
     * TODO: 通过id查询到对应数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:57
     */
    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * TODO: 根据ID集合来查询对应的数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:57
     */
//    @Override
//    public List<T> selectListByIds(List<Object> ids) {
//        return mapper.selectByIds(ids);
//    }

    /**
     * TODO: 查询整个列表
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:58
     */
    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    /**
     * TODO: 查询所有的集合数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:58
     */
    @Override
    public List<T> selectListAll() {
        return mapper.selectAll();
    }

    /**
     * TODO: 统计所有的条数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:58
     */
//    @Override
//    public Long selectCountAll() {
//        return null;
//    }

    /**
     * TODO: 查询总记录数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:58
     */
    @Override
    public Long selectCount(T entity) {
        return Long.valueOf(mapper.selectCount(entity));
    }

    /**
     * TODO: 添加
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:59
     */
    @Override
    public void insert(T entity) {
        mapper.insert(entity);
    }

    /**
     * TODO: 添加不插入null字段
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:59
     */
    @Override
    public void insertSelective(T entity) {
        mapper.insertSelective(entity);
    }

    /**
     * TODO: 删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:08
     */
    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }

    /**
     * TODO: 根据id删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:59
     */
    @Override
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * TODO: 根据id更新
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:00
     */
    @Override
    public void updateById(T entity) {
        mapper.updateByPrimaryKey(entity);
    }

    /**
     * TODO: 更新不插入null字段
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:00
     */
    @Override
    public void updateSelectiveById(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * TODO: 根据id集合批量删除
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:00
     */
//    @Override
//    public void deleteBatchByIds(List<Object> ids) {
//
//    }

    /**
     * TODO: 批量更新
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:00
     */
//    @Override
//    public void updateBatch(List<T> entitys) {
//
//    }
}
