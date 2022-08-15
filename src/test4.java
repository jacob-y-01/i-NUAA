import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class test4 {
    // 存放登录后获得的cookie
    private static Map<String, String> cookies = null;
    public static void main(String[] args) throws IOException, InterruptedException {
        // 先模拟登录获取到cookie
        login();
        // 再使用cookie 向服务器发送打卡表单
        tianxie();
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
        connect.data("username", "CZ0419030161").
        data("password", "228913995yzx@");

        //请求url获取响应信息
        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
        // 获取返回的cookie
        cookies = res.cookies();
        System.out.printf(res.body());
        System.out.printf("已成功获取到cookie");
    }
    public static void tianxie() throws IOException {
        String urlLogin = "https://m.nuaa.edu.cn/ncov/wap/default/save";
        Connection connect = Jsoup.connect(urlLogin);

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
                connect.data("sfzhux","0").
                        data("zhuxdz","").
                        data("szgj","").
                        data("szcs","").
                        data("szgjcs","").
                        data("sfjwfh","0").
                        data("sfyjsjwfh","0").
                        data("sfjcjwfh","0").
                        data("sflznjcjwfh","0").
                        data("sflqjkm","4").
                        data("jkmys","0").
                        data("sfjtgfxdq","0").
                        data("nuaaxgymjzqk","4").
                        data("dyzjzsj","").
                        data("bfhzjyq","0").
                        data("hxyxjzap","0").
                        data("yjzjsj","").
                        data("sfjkyc","0").
                        data("sftjlkjc","").
                        data("lkjctlsj","").
                        data("sfsylkjcss","").
                        data("gjzsftjlkjc","").
                        data("gjzlkjctlsj","").
                        data("gjzsfsylkjcss","").
                        data("ifhxjc","").
                        data("hsjconetime","").
                        data("hsjconeplace","").
                        data("hsjconejg","").
                        data("hsjctwotime","").
                        data("hsjctwoplace","").
                        data("hsjctwojg","").
                        data("hsjcthreetime","").
                        data("hsjcthreeplace","").
                        data("hsjcthreejg","").
                        data("hsjcfourtime","").
                        data("hsjcfourplace","").
                        data("hsjcfourjg","").
                        data("ywchxjctime","").
                        data("hsjclist","{}").
                        data("njrddz","孝陵卫49-3").
                        data("gzczxq","3").
                data("ifznqgfxljs","").
                data("iflsqgfxljs","").
                data("zrwjtw","1").
                data("jrzjtw","1").
                data("jrlvymjrq","").
                data("ifcyglq","0").
                data("wskmyy","").
                data("zhycjgdqifjn","").
                data("dqsfzgfxszqs","0").
                data("gqsfyzgfxljs","0").
                data("gqsfyqzfhryjc","0").
                data("sfyjwljqyhg","0").
                data("cjfxsfhs","").
                data("bzxyy","3").
                data("bzxyydesc","").
                data("id","28472315").
                data("uid","237271").
                data("date","20220815").
                data("tw","0").
                data("sfcxtz","0").
                data("sfyyjc","0").
                data("jcjgqr","0").
                data("jcjg","").
                data("sfjcbh","0").
                data("sfcxzysx","0").
                data("qksm","").
                data("remark","").
                data("address","江苏省南京市建邺区兴隆街道奥体大街194号南京奥林匹克体育中心").
                data("area","江苏省南京市建邺区").
                data("province","江苏省").
                data("city","南京市").
                data("geo_api_info","{\"type\":\"complete\",\"position\":{\"Q\":32.008335232205,\"R\":118.71964409722301,\"lng\":118.719644,\"lat\":32.008335},\"location_type\":\"html5\",\"message\":\"Get ipLocation failed.Get geolocation success.Convert Success.Get address success.\",\"accuracy\":65,\"isConverted\":true,\"status\":1,\"addressComponent\":{\"citycode\":\"025\",\"adcode\":\"320105\",\"businessAreas\":[{\"name\":\"江东\",\"id\":\"320106\",\"location\":{\"Q\":32.034104,\"R\":118.73183799999998,\"lng\":118.731838,\"lat\":32.034104}},{\"name\":\"奥体\",\"id\":\"320105\",\"location\":{\"Q\":32.006649,\"R\":118.72811000000002,\"lng\":118.72811,\"lat\":32.006649}},{\"name\":\"兴隆\",\"id\":\"320105\",\"location\":{\"Q\":32.020834,\"R\":118.72775200000001,\"lng\":118.727752,\"lat\":32.020834}}],\"neighborhoodType\":\"\",\"neighborhood\":\"\",\"building\":\"\",\"buildingType\":\"\",\"street\":\"奥体大街\",\"streetNumber\":\"194号\",\"country\":\"中国\",\"province\":\"江苏省\",\"city\":\"南京市\",\"district\":\"建邺区\",\"towncode\":\"320105006000\",\"township\":\"兴隆街道\"},\"formattedAddress\":\"江苏省南京市建邺区兴隆街道奥体大街194号南京奥林匹克体育中心\",\"roads\":[],\"crosses\":[],\"pois\":[],\"info\":\"SUCCESS\"}").
                data("created","1660467671").
                        data("sfzx","0").
                        data("sfjcwhry","0").
                        data("sfcyglq","0").
                        data("gllx","").
                        data("glksrq","").
                        data("jcbhlx","").
                        data("jcbhrq","").
                        data("sftjwh","0").
                        data("sftjhb","0").
                        data("fxyy","").
                        data("bztcyy","").
                        data("fjsj","0").
                        data("created_uid","0").
                        data("sfjchbry","0").
                        data("sfjcqz","").
                        data("jcqzrq","").
                        data("jcwhryfs","").
                        data("jchbryfs","").
                        data("xjzd","").
                        data("sfsfbh","0").
                        data("sfjcwzry","0").
                        data("sftjwz","0").
                        data("jhfjrq","").
                        data("jhfjjtgj","").
                        data("jhfjhbcc","").
                        data("jhfjsftjwh","0").
                        data("jhfjsftjhb","0").
                        data("szsqsfybl","0").
                        data("sfygtjzzfj","0").
                        data("gtjzzfjsj","").
                        data("sfsqhzjkk","0").
                        data("sqhzjkkys","").
                        data("is_fx_log","0").
                        data("fxzrwjtw","0").
                        data("fxjrcjtw","0").
                        data("fxjrzjtw","0").
                        data("fxsfzx","0").
                        data("fxsfcyglq","0").
                        data("fxsfcxtz","0").
                        data("sfjzxg","0").
                        data("skmcolor","1").
                        data("skmimg","").
                        data("ifjrglkjc","").
                        data("gtjjsfhm","").
                        data("gtjzsfhzl","").
                        data("sffhddjjgc","").
                        data("ifjgzgfxq","").
                        data("jgzgfxrq","").
                        data("jgzgfxdq","").
                        data("jgzgfxxxdz","").
                        data("newwchsjccs","").
                        data("dsdecjcsj","").
                        data("dsdechsjcjgtype","").
                        data("dsdrchsjcdd","").
                        data("dsdechsjcjg","").
                        data("zhyccjcsj","").
                        data("zhycchsjcjgtype","").
                        data("zhycchsjcdd","").
                        data("zhycchsjcjg","").
                        data("hsjczm","").
                        data("sfmrhs","").
                        data("gtjzryxcsfyc","1").
                        data("gtjzryxcsfycQt","").
                        data("gwszdd","").
                        data("sfyqjzgc","").
                        data("jrsfqzys","").
                        data("jrsfqzfy","").
                        data("ismoved","0\n" +
                                "\n" + "\n");
        //请求url获取响应信息
        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
        // 获取返回的cookie
        cookies = res.cookies();
        System.out.printf(res.body());
        System.out.printf("已经成功提交表单");
    }




    // 模拟点击获取地址(已作废)
//    public static void getlocal() throws IOException, InterruptedException {
//
//        WebClient wc = new WebClient(BrowserVersion.CHROME);
//        URL url1 = new URL("https://m.nuaa.edu.cn/ncov/wap/default/save");
//
//        System.out.println(cookies.size());
//
//        cookie="UUkey="+cookies.get("UUkey")+"; eai-sess="+cookies.get("eai-sess")+"; ";
//        System.out.printf(cookie);
//
//        wc.addCookie(cookie, url1, null);
//
//        HtmlPage hp=wc.getPage(url1);
//        System.out.printf(hp.toString());
//
//         //HtmlForm form= hp.getFormByName();
//         //HtmlSubmitInput button=form.getInputByName("area");
//        //点击首页上的登陆按钮，跳转到登陆页面
//        //HtmlPage loginPage = ((DomElement) hp.getByXPath("/html/body/div[1]/div/div/section/div[4]/ul/li[8]/div/input").get(0)).click();
//        HtmlPage loginPage = ((DomElement)hp.getByXPath("/html/body/div[1]/div/div/section/div[4]/ul/li[7]/div/input").get(0)).click();
//        System.out.printf(loginPage.toString());
//    }

}