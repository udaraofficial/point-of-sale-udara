package com.springbootacademy.pos_system.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

// Response
public class StandardResponse {

    private int code;
    private String message;
    private Object data;
}
