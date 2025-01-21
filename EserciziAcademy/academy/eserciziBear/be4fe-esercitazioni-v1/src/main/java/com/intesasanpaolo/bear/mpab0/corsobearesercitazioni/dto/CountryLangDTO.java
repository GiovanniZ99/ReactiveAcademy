package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto;

public class CountryLangDTO {
    private String key;

    private String language;

    public CountryLangDTO(String language) {
        this.language = language;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CountryLangDTO() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
