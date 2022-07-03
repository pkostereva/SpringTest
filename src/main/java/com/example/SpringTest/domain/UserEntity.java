package com.example.SpringTest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = com.example.SpringTest.domain.UserEntity.TABLE_NAME)
public class UserEntity {
    public static final String TABLE_NAME= "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    @ManyToOne()
    @JoinColumn(name="city_id")
    private CityEntity city;

    public UserEntity(String name, String password, CityEntity city) {
        this.name = name;
        this.password = password;
        this.city = city;
    }
}
