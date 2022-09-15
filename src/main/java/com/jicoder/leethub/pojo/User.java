package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int user_id;
    private String username;
    private String password;
    private Timestamp signup_time;
    private int dailyp_count;
    private String email;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.signup_time = new Timestamp(new Date().getTime());
        this.dailyp_count = 0;
        this.email = email;
    }

}
