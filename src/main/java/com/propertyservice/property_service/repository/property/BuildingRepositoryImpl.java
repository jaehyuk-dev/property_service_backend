package com.propertyservice.property_service.repository.property;

import com.propertyservice.property_service.dto.property.BuildingSearchCondition;
import com.propertyservice.property_service.dto.property.BuildingSummaryResponse;
import com.propertyservice.property_service.dto.property.QBuildingSummaryResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.propertyservice.property_service.domain.office.QOffice.office;
import static com.propertyservice.property_service.domain.office.QOfficeUser.officeUser;
import static com.propertyservice.property_service.domain.property.QBuilding.building;
import static com.propertyservice.property_service.domain.property.QBuildingPhoto.buildingPhoto;

@Repository
@RequiredArgsConstructor
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BuildingSummaryResponse> searchBuildingSummaryList(BuildingSearchCondition condition, long officeId) {
        return queryFactory
                .select(
                        new QBuildingSummaryResponse(
                                building.id,           // buildingId
                                building.name,         // buildingName
                                building.address,      // buildingAddress
                                buildingPhoto.photoUrl // 대표 사진 (추가할 경우)
                        ))
                .from(building)
                .leftJoin(building.picUser, officeUser)
                .leftJoin(officeUser.office, office)
                .leftJoin(buildingPhoto) // ✅ 건물 사진 조인 추가
                .on(building.id.eq(buildingPhoto.building.id)
                        .and(buildingPhoto.isMain.isTrue())) // ✅ 대표 사진 조건 추가
                .where(
                        office.officeId.eq(officeId),  // 오피스 ID로 필터링
                        containsKeyword(condition.getKeyword()) // 주소 검색 적용
                )
                .groupBy(building.id)
                .fetch();
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null; // 검색어가 없으면 조건 적용 X
        }
        return building.address.containsIgnoreCase(keyword)
                .or(building.jibunAddress.containsIgnoreCase(keyword));
    }
}
