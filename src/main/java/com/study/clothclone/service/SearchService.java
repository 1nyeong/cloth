package com.study.clothclone.service;

import com.study.clothclone.domain.SearchProduct;
import com.study.clothclone.dto.SearchProductReqDto;
import com.study.clothclone.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public int getSearchTotalCount(SearchProductReqDto searchProductReqDto){
        return searchRepository.getUserSearchProductTotalCount(searchProductReqDto);
    }

    public List<SearchProduct> getSearchProducts(SearchProductReqDto searchProductReqDto){
        searchProductReqDto.setIndex();
        return searchRepository.userSearchProduct(searchProductReqDto);
    }
}
