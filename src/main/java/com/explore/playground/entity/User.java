package com.explore.playground.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
@DynamicUpdate
@Table(name = "users", schema = "public")
@Entity
@SuppressWarnings("unused")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ssn", updatable = false, unique = true)
    private String ssn;

    @Column(name = "display_name")
    private String displayName;

    @Type(type = "jsonb")
    @Column(name = "name", columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private Name name;

    @Column(name = "monthly_income")
    private String monthlyIncome;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private Timestamp updatedOn;

    @Column(name = "created_on")
    @CreationTimestamp
    private Timestamp createdOn;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Name {

        private String firstName;
        private String lastName;
        private String middleName;
    }
}
