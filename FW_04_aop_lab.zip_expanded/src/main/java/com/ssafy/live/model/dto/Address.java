package com.ssafy.live.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private int ano;
    private int mno;
    private String title;
    private String address;
    private String detailAddress;
    private String x;
    private String y;

}
