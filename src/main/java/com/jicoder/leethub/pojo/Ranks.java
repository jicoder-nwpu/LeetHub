package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranks {

    private List<Integer> dates;
    private List<Integer> ranks;

}
