package net.togogo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/totest")
    public String totest(){
        return "test";
    }

    //测试图片上传
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile img) throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        String imgname = img.getOriginalFilename();

        //指定本地文件夹存储图片
        String filePath = "E:\\Learn\\IdeaProjects\\supermarket\\super_user\\src\\main\\resources\\static\\img\\";
        img.transferTo(new File(filePath+imgname));
        map.put("imgname",imgname);
        return map;
    }
}
