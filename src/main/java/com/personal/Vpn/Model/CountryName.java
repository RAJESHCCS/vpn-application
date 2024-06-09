package com.personal.Vpn.Model;

public enum CountryName {
    IND("001"),
    USA("002"),
    AUS("003"),
    CHI("004"),
    JPN("005");

    @Override
    public String toString() {
        return "CountryName{" +
                "code='" + code + '\'' +
                '}';
    }

    private  final String code;

    private  CountryName(String s){
        code = s;
    }
    public String toCode(){
        return this.toCode();
    }
}
