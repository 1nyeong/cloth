package com.study.clothclone.service;

import com.study.clothclone.domain.ProductLike;
import com.study.clothclone.exception.CustomLikeException;
import com.study.clothclone.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public int like(int pdtId, int userId) {
        ProductLike productLike = ProductLike.builder()
                .pdtId(pdtId)
                .userId(userId)
                .build();
        if(likeRepository.getLikeStatus(productLike) > 0){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("likeError", "좋아요를 취소해주세요.");
            throw new CustomLikeException(errorMap);
        }

        likeRepository.addLike(productLike);
        return likeRepository.getLikeCount(pdtId);
    }

    public int dislike(int pdtId, int userId){
        ProductLike productLike = ProductLike.builder()
                .pdtId(pdtId)
                .userId(userId)
                .build();
        if(likeRepository.getLikeStatus(productLike) == 0){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("likeError", "좋아요를 눌러주세요.");
            throw new CustomLikeException(errorMap);
        }

        likeRepository.deleteLike(productLike);
        return likeRepository.getLikeCount(pdtId);
    }

}
