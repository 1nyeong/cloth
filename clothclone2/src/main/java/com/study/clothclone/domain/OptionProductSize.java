package com.study.clothclone.domain;

import com.study.clothclone.dto.admin.ProductSizeOptionRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionProductSize {
    private int size_id;
    private String size_name;

    public ProductSizeOptionRespDto toDto() {
        return ProductSizeOptionRespDto.builder()
                .sizeId(size_id)
                .sizeName(size_name)
                .build();
    }
}
