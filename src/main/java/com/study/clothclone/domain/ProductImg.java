package com.study.clothclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductImg {
    private int id;
    private int pdt_Id;
    private String origin_name;
    private String save_name;
}