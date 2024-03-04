package com.binarybloom.userms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyInfo {
    @Id
    private Long id;
    private String name;
    private String principalAddress;
    private String secondaryAddress;
    private String email;
    private String principalPhoneNumber;
    private String secondaryPhoneNumber;
    private String faxNumber;
    private String taxIdentificationNumber;


}
