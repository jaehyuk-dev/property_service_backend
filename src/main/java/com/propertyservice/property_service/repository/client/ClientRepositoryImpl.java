package com.propertyservice.property_service.repository.client;

import com.propertyservice.property_service.dto.client.ClientSearchCondition;
import com.propertyservice.property_service.dto.client.ClientSummaryResponse;
import com.propertyservice.property_service.dto.client.QClientSummaryResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.propertyservice.property_service.domain.client.QClient.client;
import static com.propertyservice.property_service.domain.office.QOfficeUser.officeUser;
import static com.propertyservice.property_service.domain.office.QOffice.office;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ClientSummaryResponse> searchClientSummaryList(ClientSearchCondition condition, Long officeId) {
        return queryFactory
                .select(new QClientSummaryResponse(
                        client.id,       // clientId
                        client.name,               // clientName
                        client.status.stringValue(), // clientState (Enum이면 stringValue() 변환)
                        client.phoneNumber,        // clientPhoneNumber
                        officeUser.name,           // picManager (담당자)
                        client.source,             // clientSource
                        client.moveInDate          // moveInDate
                ))
                .from(client)
                .leftJoin(client.picUser, officeUser)
                .leftJoin(officeUser.office, office)
                .where(
                        office.officeId.eq(officeId),
                        searchByType(condition.getSearchType(), condition.getKeyword())
                )
                .fetch();
    }

    private BooleanExpression searchByType(String searchType, String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return null; // 검색어가 없으면 필터 미적용
        }

        return switch (searchType) {
            case "담당자" -> officeUser.name.containsIgnoreCase(keyword);
            case "고객" -> client.name.containsIgnoreCase(keyword);
            case "고객 전화번호" -> client.phoneNumber.containsIgnoreCase(keyword);
            default -> Expressions.booleanTemplate("false"); // ❗잘못된 경우 필터링        };
        };
    }
}
