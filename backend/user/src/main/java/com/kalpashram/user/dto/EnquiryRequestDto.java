package com.kalpashram.user.dto;

import lombok.Data;

@Data
public class EnquiryRequestDto {
    private String name;
    private String email;
    private String phone;
    private String message;
}
