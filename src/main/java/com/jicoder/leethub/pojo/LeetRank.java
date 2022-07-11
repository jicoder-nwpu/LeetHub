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

    private int rank_id;
    private int rank_val;
    private byte type;
    private int gap;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date update_time;

    private User user;

}
