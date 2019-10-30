package com.dcxxjs.core.utils;


import com.dcxxjs.core.networkinterface.SignBaseFrom;
import com.dcxxjs.core.utils.SHA256.SHA256Utils;
import com.google.gson.Gson;

import java.util.Map;
import java.util.SortedMap;

public class SHA256SiginUtils {
    /**
     *
     * @param from
     * @return
     */
    public static <T extends SignBaseFrom> String SHA256Sigin(T from)
    {
        Gson gson=new Gson();
        SortedMap<String, String> map = GsonToMap.toMap(gson.toJson(from));
        String sign = map.get("sign");
        map.put("sign", "");
        String content = SignContent(map, "&");
        return SHA256Utils.getSHA256(content);
    }


    /**
     * 签名内容
     *
     * @param entity
     * @param Joiner
     * @return
     */
    public static <T> String SignContent(T entity, String Joiner) {
        return SignContent(entity, Joiner, true);
    }

    public static <T> String SignContent(T entity, String Joiner, Boolean filternull) {
        SortedMap<String, String> map = GsonToMap.toMap(entity);
        String result = "";
        for (String key : map.keySet()) {
            String value = map.get(key);
            // 过滤null或空
            if (filternull && isEmpity(value)) {
                continue;
            }
            result += key + "=" + value + Joiner;
        }
        return result.substring(0, result.length() - 1);
    }

    public static <T> String SignContent(Map<String, String> map, String Joiner, Boolean filternull) {

        String result = "";
        for (String key : map.keySet()) {
            String value = map.get(key);
            // 过滤null或空
            if (filternull && isEmpity(value)) {
                continue;
            }
            result += key + "=" + map.get(key) + Joiner;
        }
        if(result.length()>1)
        {
            result=result.substring(0, result.length() - 1);
        }
        return result;

    }

    public static <T> String SignContent(Map<String, String> map, String Joiner) {

        return SignContent(map, Joiner, true);

    }

    public static <T> String SignContent(T entity) {

        return SignContent(entity, "&");

    }

    public static <T> String SignContent(Map<String, String> map) {

        return SignContent(map, "&");

    }

    private static Boolean isEmpity(String x) {
        if (null == x) {
            return true;
        }
        return x.isEmpty();
    }
}
