package com.study.clothclone.service;

import com.study.clothclone.dto.RegisterReqDto;

public interface AccountService {

    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception;

    public void register(RegisterReqDto registerReqDto) throws Exception;

}
