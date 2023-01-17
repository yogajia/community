package com.example.accountbook.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disburse implements Serializable {
    private int uid;
    private String type;
    private double amount;
    private String text;
    private Date create_time;

    public Disburse(int uid, String type, double amount, String text) {
        this.uid = uid;
        this.type = type;
        this.amount = amount;
        this.text = text;
    }
    public Map<String,Object> getInfoMap(){
        Map<String,Object> infoMap=new HashMap<>();
        infoMap.put("type",type);
        infoMap.put("text",text);
        infoMap.put("amount",amount);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate= dateFormat.format(create_time);
        infoMap.put("datetime",formatDate);
        return infoMap;
    }
}
