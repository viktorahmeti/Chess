package com.company.enums;

public enum Color {
    WHITE("White"), BLACK("Black");

    String value;

    Color(String s){
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
