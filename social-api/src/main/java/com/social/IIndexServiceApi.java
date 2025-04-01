package com.social;

import com.social.dto.BannersUrlDto;
import com.social.response.Response;

import java.util.List;

public interface IIndexServiceApi {
    Response<List<BannersUrlDto>> getBanner();
    Response<List<BannersUrlDto>> getWaterFall();
}
