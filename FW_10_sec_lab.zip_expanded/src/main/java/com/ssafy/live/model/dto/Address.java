package com.ssafy.live.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Address(주소)", description = "회원의 주소 정보")
public class Address {
    @Schema(description = "주소번호로 저장 시 자동 채번", requiredMode = RequiredMode.NOT_REQUIRED)
    private int ano;
    @Schema(description = "회원번호", requiredMode = RequiredMode.REQUIRED)
    private int mno;
    @Schema(description = "타이틀(분류기준)", requiredMode = RequiredMode.REQUIRED)
    private String title;
    @Schema(description = "기본 주소", requiredMode = RequiredMode.REQUIRED, example = "서울특별시 강남구 테헤란로 212")
    private String address;
    @Schema(description = "상세 주소", requiredMode = RequiredMode.REQUIRED, example = "멀티캠퍼스")
    private String detailAddress;
    @Schema(description = "x 좌표", requiredMode = RequiredMode.NOT_REQUIRED, example = "지점의 x 좌표")
    private String x;
    @Schema(description = "y 좌표", requiredMode = RequiredMode.NOT_REQUIRED, example = "지점의 y 좌표")
    private String y;

    @Schema(description = "회원 정보", requiredMode = RequiredMode.NOT_REQUIRED)
    private Member member;
}
