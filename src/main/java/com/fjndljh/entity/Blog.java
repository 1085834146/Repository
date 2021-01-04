package com.fjndljh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private long blogID;
    private String userName;
    private String title;
    private String article;
    private String blogDate;
}
