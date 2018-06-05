package com.github.wx.gadget.controller;

import com.github.wx.gadget.dbo.Tear;
import com.github.wx.gadget.service.TearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private Map<String, TearService> tearServiceMap;





    @RequestMapping("/")
    public String index() {

        tearServiceMap.entrySet().forEach(e->{
            System.out.println(e.getKey() + ":" + e.getValue());
        });

        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/test")
    public List<Tear> test() {
        return null;
    }

}