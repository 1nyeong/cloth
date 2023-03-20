package com.study.clothclone.repository;

import com.study.clothclone.domain.ProductLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {
    public int addLike(ProductLike productLike);

    public int deleteLike(ProductLike productLike);

    public int getLikeStatus(ProductLike productLike);

    public int getLikeCount(int pdtId);

}
