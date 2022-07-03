package com.example.SpringTest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = com.example.SpringTest.domain.CityEntity.TABLE_NAME)
public class CityEntity {
    public static final String TABLE_NAME = "cities";
    @Id
    private Integer id;
    private String name;
    @Column(name = "parser_alias")
    private String parserAlias;
}