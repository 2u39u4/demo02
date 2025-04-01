package com.social.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannersUrlDto {
    private Integer id;
    private String imageUrl;
    private String alt;
}
