package example.chaoyueteam.com.pocketsofanimals.speech;

import java.net.URLEncoder;

import example.chaoyueteam.com.pocketsofanimals.util.HttpUtil;

public class Text2Audio {
    public String TEXT2AUDIO_URL = "http://tsn.baidu.com/text2audio";
    public static void main(String[] args) throws Exception {
        String tex = "郑豪，陈志彬喜欢你";
        Text2Audio audio = new Text2Audio();
        audio.text2Audio(tex, "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873", "1", RandomStringGenerator.getRandomStringByLength(60));
    }
    /**
     * 所有参数方法
     * @Title text2Audio
     * @param tex	必填	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     //* @param lan	必填	语言选择,填写zh
     * @param tok	必填	开放平台获取到的开发者access_token
     * @param ctp	必填	客户端类型选择，web端填写1
     * @param cuid	必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
     * @param spd	选填	语速，取值0-9，默认为5中语速
     * @param pit	选填	音调，取值0-9，默认为5中语调
     * @param vol	选填	音量，取值0-9，默认为5中音量
     * @param per	选填	发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
     * @author 小帅丶
     * @throws Exception
     * @date 2017-5-26
     */
    @SuppressWarnings("static-access")
    public void text2Audio(String tex,String tok,String ctp,String cuid,String spd,String pit,String vol,String per) throws Exception{
        String params = "tex=" + URLEncoder.encode(tex, "UTF-8")
                + "&lan=zh&cuid=" + cuid + "&ctp=1&tok=" + tok + "&spd=" + spd
                + "&pit=" + pit + "&vol=" + vol + "&per=" + per;
        System.out.println(params);
        HttpUtil httpUtil = new HttpUtil();
        String data = httpUtil.postVoice(TEXT2AUDIO_URL,params);
        System.out.println("文件保存路径:"+data);
    }
    /**
     * 必填参数方法
     * @Title text2Audio
     * @param tex	必填	合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     //* @param lan	必填	语言选择,填写zh
     * @param tok	必填	开放平台获取到的开发者access_token
     * @param ctp	必填	客户端类型选择，web端填写1
     * @param cuid	必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
     * @author 小帅丶
     * @throws Exception
     * @date 2017-5-26
     */
    @SuppressWarnings("static-access")
    public void text2Audio(String tex,String tok,String ctp,String cuid) throws Exception{
        String params = "tex=" + URLEncoder.encode(tex, "UTF-8")
                + "&lan=zh&cuid=" + cuid + "&ctp=1&tok=" + tok;
        System.out.println(params);
        HttpUtil httpUtil = new HttpUtil();
        String data = httpUtil.postVoice(TEXT2AUDIO_URL,params);
        System.out.println("文件保存路径:"+data);
    }
}

