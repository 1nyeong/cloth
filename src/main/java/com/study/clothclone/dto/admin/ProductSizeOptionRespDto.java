package com.study.clothclone.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductSizeOptionRespDto {
    private int sizeId;
    private String sizeName;
}
