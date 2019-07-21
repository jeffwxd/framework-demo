package com.wxd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxd.bo.User;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.stream.Collector;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 17:13 2019/3/15
 */
@RestController
public class IndexController {

    private User user;

    @GetMapping("/index")
    public String index(){
        return "hello world";
    }

    public static void main(String[] args) {
        String a = "{\n" +
                "\t\"a\": [{\n" +
                "\t\t\"name\": \"小红\",\n" +
                "\t\t\"age\": \"18\"\n" +
                "\t}, {\n" +
                "\t\t\"name\": \"小明\",\n" +
                "\t\t\"age\": \"20\"\n" +
                "\t}]\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(a);
        Object a1 = jsonObject.get("a");
        String s = JSON.toJSONString(a1);
        List<User> users = JSON.parseArray(s, User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
