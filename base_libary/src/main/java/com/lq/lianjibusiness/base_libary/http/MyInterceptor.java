package com.lq.lianjibusiness.base_libary.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lq.lianjibusiness.base_libary.App.Constants;
import com.lq.lianjibusiness.base_libary.utils.MD5Utils;
import com.lq.lianjibusiness.base_libary.utils.PrefUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by ccc on 2020/9/15.
 * 描述：retrofit拦截
 */
public class MyInterceptor implements Interceptor {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");
    public static final String appKey = "test";
    public static final String secret = "123456";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        HttpUrl url = request.url();

        String params = url.encodedQuery();
        String token = PrefUtils.getString(Constants.SP_TOKEN, "");

     /*   if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            requestBuilder.addHeader("Connection", "close");
        }*/

        //获取请求方法
        String method = request.method();
        try {
            String json = "";
            Gson gson = new Gson();
            if (method.equals("POST")) {
                //post请求的封装
                Map<String, Object> map = new HashMap<>();
                if (request.body() instanceof FormBody) {
                    FormBody oldFormBody = (FormBody) request.body();
                    for (int i = 0; i < oldFormBody.size(); i++) {
                        map.put(oldFormBody.name(i), oldFormBody.value(i));
                    }

                    json = gson.toJson(map);
                } else {
                    RequestBody requestBody = request.body();
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = requestBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset();
                    }
                    json = buffer.readString(charset);
                }

                // 业务参数

                json = URLEncoder.encode(json, "utf-8");
                // 系统参数
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("name", url.toString().split(LjHost.HOST)[1]);    //请求的方法名
                param.put("app_key", appKey);
                param.put("data", json);
                param.put("timestamp", getTime());
                param.put("version", "");
                param.put("access_token", token);

                String sign = buildSign(param, secret);

                param.put("sign", sign);

                json = gson.toJson(param);

                requestBuilder.url(LjHost.HOST);
                RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json.toString().getBytes());
                requestBuilder.method(request.method(), body);
                request = requestBuilder.build();
            }

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return chain.proceed(request);
    }

    /**
     * 构建签名
     *
     * @param paramsMap 参数
     * @param secret    密钥
     * @return
     * @throws IOException
     */
    public static String buildSign(Map<String, ?> paramsMap, String secret) throws IOException {
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<String>(keySet);

        Collections.sort(paramNames);

        StringBuilder paramNameValue = new StringBuilder();

        for (String paramName : paramNames) {
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        }

        String source = secret + paramNameValue.toString() + secret;

        return MD5Utils.md5(source);
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
