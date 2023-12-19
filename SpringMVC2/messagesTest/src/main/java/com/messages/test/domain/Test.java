package com.messages.test.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Test {
    @NotBlank
    private String id;
    @NotEmpty
    private String pw;
    @Min(10)
    private int age;
}
