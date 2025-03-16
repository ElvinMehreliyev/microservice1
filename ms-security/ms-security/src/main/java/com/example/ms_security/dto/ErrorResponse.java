package com.example.ms_security.dto;

import lombok.*;

import java.util.ArrayList;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer code;
    private ArrayList<String> errors;
}
