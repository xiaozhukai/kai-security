package com.github.kai.security.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: 博客Controller
 *
 * Author: kai
 * CreateDate: 2017/9/11
 * CreateTime: 14:56
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    /**
     * TODO:文章列表展示
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:01
     */
    @RequestMapping("article")
    public String article(){
        return "blog/article/list";
    }

    /**
     * TODO: 文章修改
     *
     * Author: kai
     * CreateDate: 2017/9/11
     * CreateTime: 15:02
     */
    @RequestMapping("article/edit")
    public String articleEdit(){
        return "blog/article/edit";
    }
}
