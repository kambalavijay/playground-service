package com.example.testvalidation;

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
