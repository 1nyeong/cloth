package com.study.clothclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDtl {
    private int roleDtlId;

    private String email;
    private int roleId;

    private Role roleMst;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
