package com.explore.playground.controller.response;

import com.explore.playground.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreateResponse {

    private Long id;

    private String phone;

    private String email;

    private String ssn;

    private String displayName;

    private User.Name name;

    private String monthlyIncome;

    private String dateOfBirth;
}
