package com.github.kai.security.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * TODO:
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 23:22
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map params;

    /**
     Constructs a request object wrapping the given request.
     @exception IllegalArgumentException if the request is null */
    public ParameterRequestWrapper(HttpServletRequest request,Map newParams) {
        super(request);
        this.params = newParams;
    }

    public Map getParameterMap() {
        return params;
    }

    /**
     * TODO:  返回所有节点
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:29
     */
    public Enumeration getParameterNames(){
        Vector l = new Vector(params.keySet());
        return l.elements();
    }

    /**
     * TODO:  获取参数里面的值，并做处理结果
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 23:34
     */
    public String[] getParameterValues(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {         //包含的是一个String数组，强转成一个数组
            return (String[]) v;
        } else if (v instanceof String) {           //包含的是一个String类型的字符串，强转成一个字符串
            return new String[] {
                    (String) v
            };
        } else {
            return new String[] {                   //其他结果toString一个字符串数组
                    v.toString()
            };
        }
    }

    public String getParameter(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {             //包含的是一个String数组，强转成一个数组
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                return strArr[0];
            } else {
                return null;
            }
        } else if (v instanceof String) {               //包含的是一个String类型的字符串，强转成一个字符串
            return (String) v;
        } else {
            return v.toString();
        }
    }
}
