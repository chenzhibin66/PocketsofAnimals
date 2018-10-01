package example.chaoyueteam.com.pocketsofanimals.image;

import com.alibaba.fastjson.JSON;

import java.net.URLEncoder;

import example.chaoyueteam.com.pocketsofanimals.API.ImageApi;
import example.chaoyueteam.com.pocketsofanimals.util.Base64Util;
import example.chaoyueteam.com.pocketsofanimals.util.FileUtil;
import example.chaoyueteam.com.pocketsofanimals.util.HttpUtil;

public class AnimalDemo {
    public static void main(String[] args) throws Exception {
        //返回字符串
//		String result = getAnimalResult("G:/animal.jpg", "24.509012e6b7fd242cff7557c13dc436de.2592000.1512873097.282335-10131029");
//		System.out.println(result);
        //返回java对象
        Animal plant = getAnimalBean("C:/Users/MSI-PC/Desktop/classdesign/狮子.jpg", "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873");
        System.out.println(plant.getResult().get(0).getName());
        System.out.println(plant.getResult().get(0).getBaike_info());
    }
    /**
     * 动物识别Demo
     * @param imagePath
     * @param accessToken
     * @return 字符串
     * @throws Exception
     */
    public static String getAnimalResult(String imagePath,String accessToken) throws Exception{
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
        String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&baike_num=1";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageApi.ANIMAL_API, accessToken, param);

        System.out.println(result);
        return result;
    }
    /**
     * 动物识别Demo
     * @param imagePath
     * @param accessToken
     * @return LOGO对象
     * @throws Exception
     */
    public static Animal getAnimalBean(String imagePath,String accessToken) throws Exception{
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
        String param = "image=" + URLEncoder.encode(imgStr,"UTF-8")+"&baike_num=1";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageApi.ANIMAL_API, accessToken, param);
        Animal animal = JSON.parseObject(result,Animal.class);

        System.out.println(result);
        return animal;
    }

}
