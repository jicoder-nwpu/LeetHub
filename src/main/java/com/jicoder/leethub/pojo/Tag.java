package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private int tag_id;
    private String name;
    private int count;
    private User user;

    public Tag(String name, int count, User user){
        this.name = name;
        this.count = count;
        this.user = user;
    }

}
