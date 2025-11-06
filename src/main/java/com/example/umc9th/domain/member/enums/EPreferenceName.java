package com.example.umc9th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EPreferenceName {
    Korean("한식"),
    Japanese("일식"),
    Chineses("중식"),
    Western("양식"),
    Asian("아시안푸드"),
    Chicken("치킨"),
    SnackFood("분식"),
    Roast("구이"),
    FastFood("패스트푸드"),
    Dessert("디저트");

    private final String name;
}
