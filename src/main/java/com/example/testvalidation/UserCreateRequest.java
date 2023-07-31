package com.example.testvalidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;

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
