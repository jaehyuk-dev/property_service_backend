package com.propertyservice.property_service.domain.office;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "office_users")
@NoArgsConstructor
public class OfficeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 사용자 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office; // 중개사무소

    @Column(nullable = false, length = 255)
    private String name; // 이름

    @Column(unique = true, nullable = false, length = 255)
    private String email; // 이메일

    @Column(nullable = false, columnDefinition = "TEXT")
    private String passwordHash; // 비밀번호 (Hash Value)

    @Column(length = 255)
    private String phoneNumber; // 핸드폰번호

    @Convert(converter = RoleConverter.class)
    @Column(nullable = false)
    private Role role; // 권한 수준 (DB에서는 10 또는 20으로 저장)

    @Builder
    public OfficeUser(Office office, String name, String email, String passwordHash, String phoneNumber, Role role) {
        this.office = office;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
