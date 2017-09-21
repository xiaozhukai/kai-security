package com.github.kai.security.common.biz;

import com.github.kai.security.common.msg.TableResultResponse;
import com.github.kai.security.common.util.EntityUtils;
import com.github.kai.security.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * TODO: 抽象mapper父类
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 16:28
 */
public abstract class BaseBiz <M extends Mapper<T>,T>{

    @Autowired
    protected M mapper;

    public M getMapper() {
        return mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    /**
     * TODO:  根据参数查询一条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:31
     */
    public T selectOne(T entity){
        return mapper.selectOne(entity);
    }

    /**
     * TODO: 根据id查询出对应的一条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:33
     */
    public T selectById(Object id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * TODO: 根据参数查询List集合数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:35
     */
    public List<T> selectList(T entity){
        return mapper.select(entity);
    }

    /**
     * TODO:  查询出对应的所有数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:37
     */
    public List<T> selectListAll(){
        return mapper.selectAll();
    }

    /**
     * TODO:  根据参数统计查询条数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:38
     */
    public Long selectCount(T entity){
        return new Long(mapper.selectCount(entity));
    }

    /**
     * TODO: 如果数据存在就执行update操作，如果不存在就执行insert操作
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:41
     */
    public void insert(T entity){
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
    }

    /**
     * TODO:  insert本条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:48
     */
    public void insertSelective(T entity){
        EntityUtils.setCreateInfo(entity);
        mapper.insertSelective(entity);
    }

    /**
     * TODO: 删除本条数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:50
     */
    public void delete(T entity){
        mapper.delete(entity);
    }

    /**
     * TODO: 根据id删除数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:50
     */
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * TODO:  根据id执行update操作
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:52
     */
    public void updateById(T entity){
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }

    /**
     * TODO:  根据id只对有变化的执行update操作
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 16:55
     */
    public void updateSelectiveById(T entity){
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * TODO: 查询出实例的集合
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:06
     */
    public List<T> selectByExample(Object example){
        return mapper.selectByExample(example);
    }

    /**
     * TODO: 统计实例数量
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:06
     */
    public int selectCountByExample(Object example){
        return mapper.selectCountByExample(example);
    }

    public TableResultResponse<T> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);
        return new TableResultResponse<T>(result.getTotal(), list);
    }
}
