package com.study.clothclone.api;

import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.dto.SearchProductReqDto;
import com.study.clothclone.security.PrincipalDetails;
import com.study.clothclone.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchApi {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<CMRespDto<?>> search(SearchProductReqDto searchProductReqDto,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails != null){
            searchProductReqDto.setId(principalDetails.getUser().getId());
        }
        return ResponseEntity.ok().body(new CMRespDto<>("Successfully", searchService.getSearchProducts(searchProductReqDto)));
    }

    @GetMapping("/search/totalcount")
    public ResponseEntity<CMRespDto<Integer>> getSearchProductTotalCount(SearchProductReqDto searchProductReqDto){
        return ResponseEntity.ok().body(new CMRespDto<>("Successfully", searchService.getSearchTotalCount(searchProductReqDto)));
    }
}
