package com.ctgf.wxmes.utils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctgf.wxmes.entity.AccessToken;

import net.sf.json.JSONObject;

public class HttpsGetUtil {
    //从微信后台拿到CORPID和APPSECRET 并封装为常量
    private static final String CORPID = "ww8b92534609022c60";
    private static final String CORPSECRET  = "flwD25SIl0l7Ls1TGdOcte_YP_w9BuKvX2FipFRn2b4";;
    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";
   
    @Autowired
    private static HttpSession session;
    /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     * 
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGet(String url) throws ClientProtocolException, IOException {
        HttpClient client = HttpClientBuilder.create().build();//获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }
    
    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     * 
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
   
	public static JSONObject doPost(String url,String outStr) throws ClientProtocolException, IOException {
        HttpClient client = HttpClientBuilder.create().build();//获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }
    
    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() throws ClientProtocolException, IOException{
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("CORPID", CORPID).replace("APPSECRET", CORPSECRET);//将URL中的两个参数替换掉
        JSONObject jsonObject = doGet(url);//使用刚刚写的doGet方法接收结果
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setToken(jsonObject.getString("access_token"));//取出access_token
            token.setExpiresIn(jsonObject.getInt("expires_in"));//取出access_token的有效期
            //用于判断access_token是否过期
            
    		session.setAttribute("tokenTime", new Date()); 
    		session.setAttribute("token", token);
        }
        return token;
    }
}