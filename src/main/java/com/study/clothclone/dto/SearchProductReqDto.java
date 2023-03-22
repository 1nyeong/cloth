package com.study.clothclone.dto;

import lombok.Data;

@Data
public class SearchProductReqDto {

    private int page;
    private String searchValue;

    private int count;
    private int id;

    private int index;

    public void setIndex(){
        index = (page - 1) * count;
    }

}
