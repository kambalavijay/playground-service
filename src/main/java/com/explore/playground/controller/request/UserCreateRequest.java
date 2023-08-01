package com.explore.playground.controller.request;

import com.explore.playground.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreateRequest {

    private String phone;

    private String email;

    private String ssn;

    private String displayName;

    private User.Name name;

    private String monthlyIncome;

    private String dateOfBirth;
}
