package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.domain.property.Property;
import com.propertyservice.property_service.dto.property.PropertySearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.propertyservice.property_service.domain.property.QProperty.property;
import static com.propertyservice.property_service.domain.property.QBuilding.building;
import static com.propertyservice.property_service.domain.office.QOfficeUser.officeUser;
import static com.propertyservice.property_service.domain.office.QOffice.office;

@Repository
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Property> searchPropertyList(PropertySearchCondition condition, long officeId) {
        return queryFactory
                .select(
                        property
                )
                .from(property)
                .leftJoin(property.picUser, officeUser)
                .leftJoin(officeUser.office, office)
                .leftJoin(property.building, building)
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
            case "임대인" -> property.ownerName.containsIgnoreCase(keyword);
            case "매물 주소" -> building.address.containsIgnoreCase(keyword)
                    .or(building.jibunAddress.containsIgnoreCase(keyword))
                    .or(property.roomNumber.containsIgnoreCase(keyword));
            case "임대인 전화번호" -> property.ownerPhoneNumber.containsIgnoreCase(keyword);
            default -> Expressions.booleanTemplate("false"); // ❗잘못된 경우 필터링
        };
    }


}
