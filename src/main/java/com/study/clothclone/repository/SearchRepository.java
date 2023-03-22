package com.study.clothclone.repository;

import com.study.clothclone.domain.SearchProduct;
import com.study.clothclone.dto.SearchProductReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchRepository {

    public int getUserSearchProductTotalCount(SearchProductReqDto searchProductReqDto);

    public List<SearchProduct> userSearchProduct(SearchProductReqDto searchProductReqDto);
}
