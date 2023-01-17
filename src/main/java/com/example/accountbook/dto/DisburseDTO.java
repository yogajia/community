package com.example.accountbook.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DisburseDTO implements Serializable {
    private String type;
    private double amount;
    private String text;
    private Date create_time;
}
