package com.ssafy.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    Long id = null; // null, DB Auto Increment

    @CreatedDate
    private LocalDateTime createdDate;

    // 수정일자 자동으로 안 찍히면 나중에 수정
    @LastModifiedBy
    private LocalDateTime modifiedDate;
}
