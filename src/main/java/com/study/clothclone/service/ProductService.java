package com.study.clothclone.service;

import com.study.clothclone.dto.CheckoutRespDto;
import com.study.clothclone.dto.CollectionListRespDto;
import com.study.clothclone.dto.ProductRespDto;
import java.util.List;

public interface ProductService {
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception;
    public ProductRespDto getProduct(int pdtId) throws Exception;

    public CheckoutRespDto getCheckoutProduct(int pdtDtlId) throws Exception;
}