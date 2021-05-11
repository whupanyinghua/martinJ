package com.pyh.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.commons.lang3.StringUtils;

/**
 * 类HttpUtil的实现描述：a http tool by unirest
 *
 * @author panyinghua 2021-5-11 19:50
 */
public class HttpUtil {
    public static String sendPost(String url, String params, String contentType, String cookie) {
        String result = null;
        try {
            HttpRequestWithBody request = Unirest.post(url);
            if(StringUtils.isNotEmpty(contentType)) {
                request.header("Content-Type", contentType);
            }
            if(StringUtils.isNotEmpty(cookie)) {
                request.header("Cookie", cookie);
            }

            HttpResponse<String> res = request.body(params).asString();
            result = res.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return result;
    }
}
