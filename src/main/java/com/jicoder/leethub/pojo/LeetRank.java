package com.jicoder.leethub.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeetRank {

    public static final int DailyRank = 0;
    public static final int ContestRank = 1;
    public static final int IndexRankNum = 30;

    public static final int EASYCOUNT = 0;

    public static final int MEDIUMCOUNT = 1;

    public static final int HARDCOUNT = 2;

    private int rank_id;
    private int rank_val;
    private byte type;
    private int gap;
    private String description;
    private int contest_rank;
    private int score;
    private int easy_count;
    private int medium_count;
    private int hard_count;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date update_time;

    private User user;

}
