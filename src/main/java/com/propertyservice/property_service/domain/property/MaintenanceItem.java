package com.propertyservice.property_service.domain.property;

import com.propertyservice.property_service.domain.property.enums.MaintenanceItemType;
import com.propertyservice.property_service.domain.property.enums.MaintenanceItemTypeConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "maintenance_items")
public class MaintenanceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_item_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Convert(converter = MaintenanceItemTypeConverter.class)
    @Column(name = "maintenance_item")
    private MaintenanceItemType maintenanceItem;

    @Builder
    public MaintenanceItem(MaintenanceItemType maintenanceItem, Property property) {
        this.maintenanceItem = maintenanceItem;
        this.property = property;
    }
}

