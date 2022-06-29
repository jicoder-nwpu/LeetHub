package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;
}
