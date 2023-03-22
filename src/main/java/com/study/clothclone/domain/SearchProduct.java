package com.study.clothclone.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchProduct {
    private int id;
    private String pdt_name;
    private int pdt_price;
    private String saveName;
}
