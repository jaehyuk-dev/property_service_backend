package com.propertyservice.property_service.domain.schedule;

import com.propertyservice.property_service.domain.client.Client;
import com.propertyservice.property_service.domain.common.BaseEntity;
import com.propertyservice.property_service.domain.office.OfficeUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_user_id", nullable = false)
    private OfficeUser picUser; // 담당자 (중개사무소 사용자 ID)

    @Column(name = "schedule_date", length = 255)
    private LocalDateTime date; // 일시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // 고객 ID

    @Convert(converter = ScheduleTypeConverter.class)
    @Column(name = "schedule_type", nullable = false)
    private ScheduleType scheduleType; // 일정 유형 (21: 상담, 22: 계약, 23: 일주)

    @Column(name = "schedule_remark", length = 255)
    private String remark; // 일정 특이사항

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted = false; // 완료 여부 (기본값: false)
}
