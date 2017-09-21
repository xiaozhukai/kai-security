package com.github.kai.security.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by kai on 2017/9/17.
 */
public class JsonUtils {
    /**
     * 将JSONObjec对象转换成Map集合
     * @param json
     * @return
     */
    public static Map<String, Object> reflect(JSONObject json){
        Map<String, Object> map = new HashMap<String, Object>();
        Set keys = json.keySet();
        for(Object key : keys){
            Object o = json.get(key);
            if(o instanceof JSONArray)
                map.put((String) key, reflect((JSONArray) o));
            else if(o instanceof JSONObject)
                map.put((String) key, reflect((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static Object reflect(JSONArray json){
        List<Object> list = new ArrayList<Object>();
        for(Object o : json){
            if(o instanceof JSONArray)
                list.add(reflect((JSONArray) o));
            else if(o instanceof JSONObject)
                list.add(reflect((JSONObject) o));
            else
                list.add(o);
        }
        return list;
    }

    public static Map<String,Object> analysisMap(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        Map<String, Object> resultMap = JsonUtils.reflect(jsonObject);
        return resultMap;
    }
}
