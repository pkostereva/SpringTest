package com.example.SpringTest.models.requestModels;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @Min(1)
    @Max(3)
    private Integer cityId;
}
