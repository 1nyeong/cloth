package com.study.clothclone.repository;

import com.study.clothclone.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {

    public User findUserByEmail(String email) throws Exception;

    public int saveUser(User user) throws Exception;

    public int updateProvider(User user) throws Exception;

}