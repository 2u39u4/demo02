package com.social.homepage.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WaterFallService {

    @Resource
    private RestTemplate restTemplate;

    public List<String> getWaterFall()
    {
        String url = "https://img.xjh.me/random_img.php?return=url";
        List<String> result = new ArrayList<>();
        for(int i=0;i<3;i++){
            String imageUrl=restTemplate.getForObject(url, String.class);
            //给imageUrl首部拼接https:
            imageUrl = "https:"+imageUrl;
            result.add(imageUrl);
        }
        return result;
    }
}
