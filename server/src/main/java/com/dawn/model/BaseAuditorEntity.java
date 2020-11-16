package com.dawn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Setter
public abstract class BaseAuditorEntity {

    // TODO custom serializer and deserializer 적용
    // https://tramyu.github.io/java/spring/jpa-auditing/
    @JsonIgnore
    @Column(name = "created_at", nullable =  false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    // TODO custom serializer and deserializer 적용
    @JsonIgnore
    @Column(name = "modified_at", nullable =  false, updatable = true)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
