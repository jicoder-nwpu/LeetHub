package com.jicoder.leethub.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    public static final int COMMONPROBLEM = 0;
    public static final int DAILYPROBLEM = 1;
    public static final int COMPETITIONPROBLEM = 2;

    private int problem_id;
    private String title;
    private String questionId;
    private int type;
    private String difficulty;
    private String url;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;

}
