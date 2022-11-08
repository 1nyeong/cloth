package com.study.clothclone.service;

import com.study.clothclone.domain.User;
import com.study.clothclone.dto.RegisterReqDto;
import com.study.clothclone.exception.CustomInternalServerErrorException;
import com.study.clothclone.exception.CustomValidationException;
import com.study.clothclone.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception {
        // 이메일 중복확인
        User user = accountRepository.findUserByEmail(registerReqDto.getEmail());

        if (user != null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소입니다.");

            throw new CustomValidationException("Duplicate email", errorMap);
        }
    }

    @Override
    public void register(RegisterReqDto registerReqDto) throws Exception {
        // 회원가입 진행
        User user = registerReqDto.toEntity();
        int result = accountRepository.saveUser(user);
        if (result == 0) {
            throw new CustomInternalServerErrorException("회원가입 중 문제가 발생하였습니다");
        }
    }
}
