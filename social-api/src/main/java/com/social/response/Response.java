package com.social.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
//Response.<List<T>>builder().code(1).msg("success").data(list).build();
