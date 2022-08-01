package com.jicoder.leethub.utils;

import com.jicoder.leethub.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTagList {

    private int status;
    private String list;
    private String message;

}
