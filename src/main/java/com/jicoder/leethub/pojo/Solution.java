package com.jicoder.leethub.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution {

    public static int SOLUTION_COUNT = 5;
    public static int SOLUTION_DRAFT_TYPE = 0;
    public static int SOLUTION_FORMAL_TYPE = 1;

    private int solution_id;
    private String title;
    private String context;
    private int type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp update_time;

    private User user;
    private Problem problem;

    public Solution(String title, String context, Timestamp update_time, int type, User user, Problem problem){
        this.title = title;
        this.context = context;
        this.update_time = update_time;
        this.type = type;
        this.user = user;
        this.problem = problem;
    }

    public boolean equals(Solution solution){
        if(this.type != solution.type){
            return false;
        }
        if(!this.title.equals(solution.getTitle())){
            return false;
        }
        if(!this.context.equals(solution.getContext())){
            return false;
        }
        return true;
    }

}
