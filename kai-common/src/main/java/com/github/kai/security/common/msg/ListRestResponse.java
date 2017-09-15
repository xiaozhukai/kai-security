package com.github.kai.security.common.msg;

/**
 * TODO: 响应信息
 *
 * Author: kai
 * CreateDate: 2017/9/4
 * CreateTime: 19:12
 */
public class ListRestResponse<T> {
    boolean rel;
    String msg;
    T result;
    int count;
    String callback;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }


    /**
     * TODO: 返回int类型的此实体的次数
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:17
     */
    public ListRestResponse count(Long count){
        this.setCount(count.intValue());
        return this;
    }

    public ListRestResponse count(int count) {
        this.setCount(count);
        return this;
    }

    /**
     * TODO: 返回结果的boolean值
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:18
     */
    public ListRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }

    /**
     * TODO: 返回msg信息
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:19
     */
    public ListRestResponse msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    /**
     * TODO:  返回实体结果
     *
     * Author: kai
     * CreateDate: 2017/9/4
     * CreateTime: 19:19
     */
    public ListRestResponse result(T result) {
        this.setResult(result);
        return this;
    }
}
