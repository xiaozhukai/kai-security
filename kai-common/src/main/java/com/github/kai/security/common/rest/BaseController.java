package com.github.kai.security.common.rest;


import com.github.kai.security.common.biz.BaseBiz;
import com.github.kai.security.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO: 父类Controller
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 19:23
 */
public class BaseController<Biz extends BaseBiz,Entity> {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;                                  //继承了父类，拥有父类的属性息

    /**
     * TODO: Restful格式的POST请求，执行insert添加功能
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 21:45
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Entity> add(Entity entity){
        //插入该entity
        baseBiz.insertSelective(entity);
        //插入成功设置一个成功值
        return new ObjectRestResponse<Entity>().rel(true);
    }

    /**
     * TODO: Restful格式的GET请求，通过id查询到对应的实体
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 21:52
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable int id){
        //基础映射了直接通过id查询到对应的参数
        return new ObjectRestResponse<Entity>().rel(true).result(baseBiz.selectById(id));
    }

    /**
     * TODO: Restful格式的PUT请求，通过id进行更新操作
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 21:58
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(Entity entity){
        //执行更新操作
        baseBiz.updateSelectiveById(entity);
        //返回结果
        return new ObjectRestResponse<Entity>().rel(true);
    }

    /**
     * TODO: Restful格式的DELETE请求，根据id删除数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:15
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    /**
     * TODO: Restful格式的GET请求，查询所有数据
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:22
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> list(){
        return baseBiz.selectListAll();
    }

    /**
     * TODO: 获取到当前用户名
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 22:27
     */
    public String getCurrentUserName(){
        //获取到头信息
        String authorization = request.getHeader("Authorization");
        //设置Base64编码解析
        return new String(Base64Utils.decodeFromString(authorization));
    }

}
