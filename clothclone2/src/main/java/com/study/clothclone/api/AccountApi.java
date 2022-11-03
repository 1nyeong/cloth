package com.study.clothclone.api;

import com.study.clothclone.aop.annotation.ValidAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.study.clothclone.aop.annotation.LogAspect;
import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.dto.RegisterReqDto;
import com.study.clothclone.dto.validation.ValidationSequence;
import com.study.clothclone.service.AccountService;
import java.net.URI;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto,
                                      BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(registerReqDto);
        accountService.register(registerReqDto);

        return ResponseEntity.created(URI.create("/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail()));
    }
}