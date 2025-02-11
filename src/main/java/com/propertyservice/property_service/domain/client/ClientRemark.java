package com.propertyservice.property_service.domain.client;

import com.propertyservice.property_service.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client_remark")
@NoArgsConstructor
public class ClientRemark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_remark_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "client_remark", nullable = false, length = 255)
    private String remark;

    // 생성자 추가 (편의 메서드)
    @Builder
    public ClientRemark(Client client, String remark) {
        this.client = client;
        this.remark = remark;
    }
}
