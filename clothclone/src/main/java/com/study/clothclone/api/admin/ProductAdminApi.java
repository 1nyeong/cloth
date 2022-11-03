package com.study.clothclone.api.admin;


import com.study.clothclone.aop.annotation.LogAspect;
import com.study.clothclone.aop.annotation.ValidAspect;
import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.dto.admin.ProductImgReqDto;
import com.study.clothclone.dto.admin.ProductRegisterDtlReqDto;
import com.study.clothclone.dto.admin.ProductRegisterReqDto;
import com.study.clothclone.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) throws Exception {
//        String name = productRegisterReqDto.getName();
//
//        Random random = new Random();
//
//        for(int i = 0; i < 100; i++) {
//
//            productRegisterReqDto.setCategory(i / 10 + 1);
//            productRegisterReqDto.setName(name + (i + 1));
//            productRegisterReqDto.setPrice((random.nextInt(10) + 1) * 100000);
//            productManagementService.registerMst(productRegisterReqDto);
//
//        }
        productManagementService.registerMst(productRegisterReqDto);

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {

        return ResponseEntity.ok()
                .body(new CMRespDto<>("Get Successfully", productManagementService.getCategoryList()));
    }

    @GetMapping("/option/products/mst")
    public ResponseEntity<?> getProductMstList() throws Exception {
        return ResponseEntity.ok()
                .body(new CMRespDto<>("Get Successfully", productManagementService.getProductMstList()));
    }

    @GetMapping("/option/products/size/{productId}")
    public ResponseEntity<?> getSizeList(@PathVariable int productId) throws Exception {
        return ResponseEntity.ok()
                .body(new CMRespDto<>("Get Successfully", productManagementService.getSizeList(productId)));
    }

    @PostMapping("/product/dtl")
    public ResponseEntity<?> registerDtl(@RequestBody ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {

        productManagementService.checkDuplicatedColor(productRegisterDtlReqDto);
        productManagementService.registerDtl(productRegisterDtlReqDto);

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

    @PostMapping("/product/img")
    public ResponseEntity<?> registerImg(ProductImgReqDto productImgReqDto) throws Exception {

        productManagementService.registerImg(productImgReqDto);

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

}