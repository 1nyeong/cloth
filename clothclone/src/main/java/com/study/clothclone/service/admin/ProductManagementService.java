package com.study.clothclone.service.admin;

import com.study.clothclone.domain.Product;
import com.study.clothclone.dto.admin.*;

import java.util.List;


public interface ProductManagementService {

    public List<CategoryResponseDto> getCategoryList() throws Exception;

    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

    public List<ProductMstOptionRespDto> getProductMstList() throws Exception;

    public List<?> getSizeList(int productId) throws Exception;

    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;

    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;

    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception;
}
