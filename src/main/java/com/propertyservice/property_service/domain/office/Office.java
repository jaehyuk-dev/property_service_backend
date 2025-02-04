package com.propertyservice.property_service.domain.office;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "office")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId; // 중개사무소 ID

    @Column(unique = true, length = 10)
    private String officeCode; // 중개사무소 코드

    @Column(nullable = false, length = 255)
    private String officeName; // 중개사무소 상호명

    @Column(nullable = false, length = 5)
    private String zonecode; // 우편번호

    @Column(nullable = false, length = 255)
    private String officeAddress; // 주소

    @Column(length = 255)
    private String addressDetail; // 상세주소

    @Column(length = 255)
    private String presidentName; // 대표자명

    @Column(length = 255)
    private String presidentEmail; // 대표자 이메일

    @Column(length = 255)
    private String mobileNumber; // 대표자 핸드폰번호

    @Column(length = 255)
    private String phoneNumber; // 전화번호

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfficeUser> officeUserList = new ArrayList<>(); // 사용자 목록


    @Builder
    public Office(String officeName, String zonecode, String officeAddress, String addressDetail, String presidentName, String presidentEmail, String mobileNumber, String phoneNumber) {
        this.officeName = officeName;
        this.zonecode = zonecode;
        this.officeAddress = officeAddress;
        this.addressDetail = addressDetail;
        this.presidentName = presidentName;
        this.presidentEmail = presidentEmail;
        this.mobileNumber = mobileNumber;
        this.phoneNumber = phoneNumber;
    }
}
