import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class test4 {

    // 每日日期
    public static String DATE="2022819";
    // 存放登录后获得的cookie
    public static Map<String, String> cookies = new HashMap<String, String>();
    // 用户名和密码
    public static Map<String,String> UserPass=new HashMap<String, String>();
    public static  String username,password;
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. 数据初始化,读取信息用户,读取表单信息
        init();
        // 2. 获取今日日期
        getdate();

        try {

            while (!UserPass.isEmpty()) {
                // 获取用户id和密码
                for (String key : UserPass.keySet()) {
                    username = key;
                    password = UserPass.get(key);
                }
                    // 登录
                    login();
                    // 4. 再使用cookie 向服务器发送打卡表单
                    // 如果打卡成功，返回true
                    if (tianxie()) {
                        System.out.println(username + "已打卡");
                        UserPass.remove(username);
                    }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void init()
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("user.txt"));
            // 将用户信息挨个写入集合
            String a=new String();
            String b=new String();
           while (((a=reader.readLine())!=null)|((b=reader.readLine())!=null))
           {
               UserPass.put(a,b);
               System.out.println(a+b);
           }
           reader.close();
        }catch (Exception e)
        {
            System.out.printf(e.getMessage());
        }
        System.out.println("已读取完毕user.txt");
    }
    public static void getdate()
    {
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 0);//如果把0修改为-1就代表昨天
        date = calendar.getTime();
        SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
        DATE = format.format(date);
        System.out.println("已获取今日日期"+DATE);
    }
    public static void login() throws IOException {
        //POST https://m.nuaa.edu.cn/uc/wap/login/check HTTP/1.1
        String urlLogin = "https://m.nuaa.edu.cn/uc/wap/login/check";
        Connection connect = Jsoup.connect(urlLogin);
        // 伪造请求头
        connect.header("Host", "m.nuaa.edu.cn").
                header("Connection","keep-alive").
                header("Content-Length", "46").
                header("Accept", "application/json, text/javascript, */*; q=0.01");
        connect.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connect.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").
                header("Referer", ": https://m.nuaa.edu.cn/uc/wap/login?redirect=https%3A%2F%2Fm.nuaa.edu.cn%2Fncov%2Fwap%2Fdefault%2Findex").
                header("X-Requested-With", "XMLHttpRequest");
        connect.header("Accept-Encoding", "gzip, deflate");
        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");


        //**********
        //  登陆信息
        //**********
        connect.data("username", username).
        data("password", password);

        //请求url获取响应信息
        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
        // 获取返回的cookie
        cookies = res.cookies();
        System.out.println(res.body());
        System.out.println("已成功获取到cookie");
    }
    public static boolean tianxie() throws IOException {
        String urlLogin = "https://m.nuaa.edu.cn/ncov/wap/default/save";
        Connection connect = Jsoup.connect(urlLogin);

        // 伪造包头
        connect.header("Host","m.nuaa.edu.cn").
                header("Connection","keep-alive").
                header("Content-Length","3939").
                header("sec-ch-ua","\" Not;A Brand\";v=\"99\", \"Microsoft Edge\";v=\"103\", \"Chromium\";v=\"103\"").
                header("Accept","application/json, text/javascript, */*; q=0.01").
                header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8").
                header("X-Requested-With","XMLHttpRequest").
                header("sec-ch-ua-mobile","?0").
                header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.114 Safari/537.36 Edg/103.0.1264.62").
                header("sec-ch-ua-platform","\"Windows\"").
                header("Origin","https://m.nuaa.edu.cn").
                header("Sec-Fetch-Site","same-origin").
                header("Sec-Fetch-Mode","cors").
                header("Sec-Fetch-Dest","empty").
                header("Referer","https://m.nuaa.edu.cn/ncov/wap/default/index").
                header("Accept-Encoding","gzip, deflate, br").
                header("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        // 加入cookie
                connect.cookies(cookies);
        // 写入表单信息
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("message.txt"));
            // 获取到一行内容
            String temp=new String();
            while (((temp=reader.readLine())!=null))
            {
                // 将一行分成两段
                String []tokens=temp.split("\t");
                // 用于判断被分成了几段
                int num=0;
                String []str=new String[2];
                for (String s:tokens)
                {
                    str[num]=s;
                    num++;
                }
                if (str[0].equals("date"))
                {
                    //********************
                    //改日期
                    // ********************
                    connect.data("date",DATE);
                } else if (str[1]==null) {
                    connect.data("str[0","");
                } else {
                    connect.data(str[0], str[1]);
                }
                //System.out.println(str[0]+str[1]);
            }
            reader.close();
        }catch (Exception e)
        {
            System.out.printf(e.getMessage());
        }
        System.out.println("已读取完毕message.txt");


        //请求url获取响应信息
        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
        // 获取返回的cookie
        cookies = res.cookies();
        String response=res.body();
        if (response.charAt(5)=='0') {
            System.out.println("已发送表单");
            return true;
        }
        else
        {
            System.out.println("表单发送失败");
            return false;
        }
    }
}