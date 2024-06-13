package com.personal.Vpn.Model;

public enum CountryName {
    IND("001"),
    USA("002"),
    AUS("003"),
    CHI("004"),
    JPN("005");


    private final String code;
    private CountryName(String s) {
        code = s;
    }

//    public static boolean equalsIgnoreCase(String string) {
////        return string;
//    }

    public String toCode() {
        return this.code;
    }

//    public String getCode() {
//        return code;
//    }
//
//    CountryName(String code) {
//        this.code = code;
    }









