package com.app.library.domain.entity.member;

public enum MemberType {

    STUDENT("student"), REGULAR("regular");

    private final String memberType;

    MemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return memberType;
    }
}
