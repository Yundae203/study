package com.messages.test.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberIdx;

    private String memberId;

    private String memberPw;

    private Integer memberAge;

    @Builder
    public Member(Long memberIdx, String memberId, String memberPw, Integer memberAge) {
        this.memberIdx = memberIdx;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberAge = memberAge;
    }
}
