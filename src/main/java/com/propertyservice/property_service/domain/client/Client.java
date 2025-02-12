package com.propertyservice.property_service.domain.client;

import com.propertyservice.property_service.domain.client.enums.ClientStatus;
import com.propertyservice.property_service.domain.client.enums.ClientStatusConverter;
import com.propertyservice.property_service.domain.common.BaseEntity;
import com.propertyservice.property_service.domain.common.eums.Gender;
import com.propertyservice.property_service.domain.common.eums.GenderConverter;
import com.propertyservice.property_service.domain.office.OfficeUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "client")
@NoArgsConstructor
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_user_id", nullable = false)
    private OfficeUser picUser;

    @Convert(converter = ClientStatusConverter.class)
    @Column(name = "client_status", nullable = false)
    private ClientStatus status;

    @Column(name = "client_name", length = 255)
    private String name;

    @Column(name = "client_phone_number", nullable = false, length = 255)
    private String phoneNumber;

    @Convert(converter = GenderConverter.class)
    @Column(name = "client_gender", nullable = false)
    private Gender gender;

    @Column(name = "client_source", length = 255)
    private String source;

    @Column(name = "client_type", length = 255)
    private String type;

    @Column(name = "client_move_in_date")
    private LocalDate moveInDate;

    @Builder
    public Client(OfficeUser picUser, ClientStatus status, String name, String phoneNumber, Gender gender, String source, String type, LocalDate moveInDate) {
        this.picUser = picUser;
        this.status = status;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.source = source;
        this.type = type;
        this.moveInDate = moveInDate;
    }
}
