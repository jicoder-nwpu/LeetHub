package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranks {

    private List<Date> dates;
    private List<Integer> ranks;

}
