package com.social.http;

import com.social.IIndexServiceApi;
import com.social.dto.BannersUrlDto;
import com.social.homepage.service.WaterFallService;
import com.social.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/index")
public class IndexController implements IIndexServiceApi {
    @GetMapping("/getBanner")
    public Response<List<BannersUrlDto>> getBanner() {
        List<BannersUrlDto> list = List.of(
                new BannersUrlDto(0, "http://localhost:8080/banners/image0.png", "banner 0"),
                new BannersUrlDto(1, "http://localhost:8080/banners/image1.png", "banner 1"),
                new BannersUrlDto(2, "http://localhost:8080/banners/image2.png", "banner 2")
        );
        return Response.<List<BannersUrlDto>>builder().code(1).msg("success").data(list).build();
    }

    @Autowired
    private WaterFallService waterFallService;

    @GetMapping("/getWaterFall")
    public Response<List<BannersUrlDto>> getWaterFall(){
        List<BannersUrlDto> list = new ArrayList<>();
        List<String> used=waterFallService.getWaterFall();
        used.forEach(url->{
            list.add(new BannersUrlDto(0,url,"emm"));
        });
        return Response.<List<BannersUrlDto>>builder().code(1).msg("success").data(list).build();
    }
}
