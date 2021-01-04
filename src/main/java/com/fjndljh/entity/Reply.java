package com.fjndljh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private long replyID;
    private long blogID;
    private String userName;
    private String replyWords;
    private String replyTime;
}
