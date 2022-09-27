package com.jicoder.leethub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    public static int EXPERIENCE_TYPE_RELEASE = 1;
    public static int EXPERIENCE_TYPE_DRAFT = 0;
    public static int EXPERIENCE_ID_NULL = -1;

    private int experience_id;
    private String title;
    private String content;
    private Timestamp update_time;
    private User user;
    private String label;
    private int type;

    public Experience(String title, String content, String label, User user, int type){
        this.title = title;
        this.content = content;
        this.label = label;
        this.user = user;
        this.type = type;
    }

    public boolean equals(Experience experience){
        if(!title.equals(experience.getTitle())){
            return false;
        }
        if(!content.equals(experience.getContent())){
            return false;
        }
        if(!label.equals(experience.getLabel())){
            return false;
        }
        if(type != experience.getType()){
            return false;
        }
        return true;
    }

}
