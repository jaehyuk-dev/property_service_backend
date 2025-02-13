package com.propertyservice.property_service.domain.property;

import com.propertyservice.property_service.domain.property.enums.PropertyOptionConverter;
import com.propertyservice.property_service.domain.property.enums.PropertyOptionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "property_options")
public class PropertyOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_option_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Convert(converter = PropertyOptionConverter.class)
    @Column(name = "option_type", nullable = false)
    private PropertyOptionType optionType;

    @Builder
    public PropertyOption(Property property, PropertyOptionType optionType) {
        this.property = property;
        this.optionType = optionType;
    }
}
