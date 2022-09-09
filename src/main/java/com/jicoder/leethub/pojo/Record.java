package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private String title;
    private String difficulty;
    private Timestamp submit_time;
    private String url;
    private int problem_id;
    private Integer solution_id;

}
