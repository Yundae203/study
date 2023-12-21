package com.messages.test.web.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberForm {

    @NotBlank
    private String memberId;
    @NotBlank
    private String memberPw;
    @NotBlank
    private String memberName;
    @Min(14)
    private int memberAge;

}
