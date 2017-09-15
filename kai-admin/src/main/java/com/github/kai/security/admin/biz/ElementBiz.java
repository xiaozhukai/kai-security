package com.github.kai.security.admin.biz;

import com.github.kai.security.admin.entity.Element;
import com.github.kai.security.admin.mapper.ElementMapper;
import com.github.kai.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: 元素节点
 *
 * Author: kai
 * CreateDate: 2017/9/6
 * CreateTime: 15:51
 */
@Service
public class ElementBiz extends BaseBiz<ElementMapper,Element>{
    /**
     * TODO: 通过userId查询授权节点信息
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:50
     */
    public List<Element> getAuthorityElementByUserId(String userId){
        return mapper.selectAuthorityElementByUserId(userId);
    }

    /**
     * TODO: 通过userId和menuId查询出授权按钮
     *
     * Author: kai
     * CreateDate: 2017/9/6
     * CreateTime: 18:50
     */
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);

    }

}
