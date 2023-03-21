package com.study.clothclone.api;

import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.security.PrincipalDetails;
import com.study.clothclone.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeApi {

    private final LikeService likeService;

    @PostMapping("/product/{pdtId}/like")
    public ResponseEntity<CMRespDto<Integer>> like(@PathVariable int pdtId,
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {

        int likeCount = likeService.like(pdtId, principalDetails.getUser().getId());

        return ResponseEntity.ok().body(new CMRespDto<>("Successfully", likeCount));
    }

    @DeleteMapping("/product/{pdtId}/like")
    public ResponseEntity<CMRespDto<Integer>> dislike(@PathVariable int pdtId,
                                                      @AuthenticationPrincipal PrincipalDetails principalDetails){

        int likeCount = likeService.like(pdtId, principalDetails.getUser().getId());

        return ResponseEntity.ok().body(new CMRespDto<>("Successfully", likeCount));
    }

}
