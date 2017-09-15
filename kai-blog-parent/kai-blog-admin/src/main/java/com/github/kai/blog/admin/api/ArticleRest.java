package com.github.kai.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.kai.blog.admin.biz.ArticleBiz;
import com.github.kai.blog.admin.entity.Article;
import com.github.kai.security.common.msg.ListRestResponse;
import com.github.kai.security.common.msg.ObjectRestResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 文章API
 *
 * Author: kai
 * CreateDate: 2017/9/12
 * CreateTime: 13:05
 */
@RestController
@RequestMapping("api/article")
public class ArticleRest {

    @Autowired
    private ArticleBiz articleBiz;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Article>().rel(true).result(articleBiz.selectById(id)));
    }

    /**
     * TODO: 展示文章页面所有的数据
     *
     * Author: kai
     * CreateDate: 2017/9/12
     * CreateTime: 14:20
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String callback){
        Page<Article> objects = PageHelper.startPage(pageIndex, pageSize);
        articleBiz.selectListAll();
        return new JSONPObject(callback, new ListRestResponse<Article>().rel(true).count(objects.getTotal()).result(objects.getResult()));
    }
}
