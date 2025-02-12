package com.propertyservice.property_service.domain.client;

import com.propertyservice.property_service.domain.common.TransactionType;
import com.propertyservice.property_service.domain.common.TransactionTypeConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client_expected_transaction_type")
@NoArgsConstructor
public class ClientExpectedTransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expectedTransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(name = "expected_transaction_type", nullable = false)
    private TransactionType expectedTransactionType;

    @Builder
    public ClientExpectedTransactionType(Client client, TransactionType expectedTransactionType) {
        this.client = client;
        this.expectedTransactionType = expectedTransactionType;
    }
}
