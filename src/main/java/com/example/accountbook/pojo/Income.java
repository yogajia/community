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
@NoArgsConstructor
@AllArgsConstructor
public class Income implements Serializable {
    private int uid;
    private String type;
    private double amount;
    private Date create_time;
    private String text;
    public  Income(int id,String type,double amount,String text){
        uid=id;
        this.text=text;
        this.amount=amount;
        this.type=type;
    }

    public Map<String,Object> getInfoMap(){
        Map<String,Object>infoMap=new HashMap<>();
        infoMap.put("type",type);
        infoMap.put("text",text);
        infoMap.put("amount",amount);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate= dateFormat.format(create_time);
        infoMap.put("datetime",formatDate);
        return infoMap;
    }
}
