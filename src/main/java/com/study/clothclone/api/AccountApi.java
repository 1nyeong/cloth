package com.study.clothclone.api;

import com.study.clothclone.aop.annotation.ValidAspect;
import com.study.clothclone.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.study.clothclone.aop.annotation.LogAspect;
import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.dto.RegisterReqDto;
import com.study.clothclone.dto.validation.ValidationSequence;
import com.study.clothclone.service.AccountService;
import java.net.URI;

@Slf4j
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

    @GetMapping("/principal")
    public ResponseEntity<CMRespDto<? extends PrincipalDetails>> getPrincipalDetails(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails != null) {
            principalDetails.getAuthorities().forEach(role -> {
                log.info("로그인된 사용자의 권한: {}", role.getAuthority());
            });
        }

        return ResponseEntity.ok().body(new CMRespDto<>("Successfully", principalDetails));
    }
}