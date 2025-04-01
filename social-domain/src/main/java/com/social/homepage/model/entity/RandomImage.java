package com.social.homepage.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandomImage {
    private Integer id;
    private String imgUrl;
    private String alt;
}
