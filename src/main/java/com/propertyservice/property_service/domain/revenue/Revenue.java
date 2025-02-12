package com.propertyservice.property_service.domain.revenue;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.client.ShowingProperty;
import com.propertyservice.property_service.domain.common.BaseEntity;
import com.propertyservice.property_service.domain.property.Property;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "revenues")
public class Revenue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showing_property_id", nullable = false)
    private ShowingProperty showingProperty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "commision_fee")
    private Long commissionFee;

    @Column(name = "move_in_date")
    private LocalDate moveInDate;

    @Column(name = "move_out_date")
    private LocalDate moveOutDate;

    @Builder
    public Revenue(ShowingProperty showingProperty, Client client, Property property, Long commissionFee, LocalDate moveInDate, LocalDate moveOutDate) {
        this.showingProperty = showingProperty;
        this.client = client;
        this.property = property;
        this.commissionFee = commissionFee;
        this.moveInDate = moveInDate;
        this.moveOutDate = moveOutDate;
    }
}
