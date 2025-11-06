package com.springboot.umc9th.domain.store.entity;

import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Local")
public class Local extends BaseEntity {

    @Column(name ="local",length = 50)
    private String name;

    @OneToMany(mappedBy = "local")
    private List<Store> storeList =new ArrayList<>();
}
