package com.example.Project0.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
/*
 * When applied to the subclasses the inherited mappings will apply in the context of the subclass tables.
 * Mapping information may be overridden in such subclasses by using the <code>AttributeOverride</code> and <code>AssociationOverride</code> annotations or corresponding XML elements.
 */
@EntityListeners(AuditingEntityListener.class)
/*
 * Specifies the callback listener classes to be used for an entity or mapped superclass.
 * 자식 Entity 즉 상속받은 Entity 클래스가 무슨 기능을 수행하는지 감지하는 어노테이션
 */
@Getter
public abstract class BaseEntity {

    @CreationTimestamp // createTime 즉 insert가 수행된 시간
    @Column(updatable = false) // update 할 때 값이 들어가지 않게
    private LocalDateTime createDateTime; // insert 수행한 시간.

    @CreationTimestamp // updateTime 즉 update가 수행된 시간
    @Column(updatable = false) // insert 할 때 값이 들어가지 않게
    private LocalDateTime updateDateTime; // update 수행한 시간.
}
