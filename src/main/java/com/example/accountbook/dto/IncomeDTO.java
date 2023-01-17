package com.example.accountbook.dto;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class IncomeDTO implements Serializable {
    private String type;
    private double amount;
    private Date create_time;
    private String text;
}
