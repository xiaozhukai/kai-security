package com.github.kai.blog.admin.rest;

import com.github.kai.blog.admin.biz.ArticleBiz;
import com.github.kai.blog.admin.entity.Article;
import com.github.kai.security.common.msg.TableResultResponse;
import com.github.kai.security.common.rest.BaseController;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

/**
 * TODO: 文章controller
 *
 * Author: kai
 * CreateDate: 2017/9/12
 * CreateTime: 13:38
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController<ArticleBiz,Article> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Article> page(int limit, int offset, String title){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<Article>(count,baseBiz.selectByExample(example));
    }
}
