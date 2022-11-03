package com.study.clothclone.repository;

import com.study.clothclone.domain.CollectionsProduct;
import com.study.clothclone.domain.PaymentProduct;
import com.study.clothclone.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<CollectionsProduct> getProductList(Map<String, Object> map) throws Exception;

    public Product getProduct(int pdtId) throws Exception;

    public PaymentProduct getPaymentProduct(int pdtDtlId) throws Exception;
}