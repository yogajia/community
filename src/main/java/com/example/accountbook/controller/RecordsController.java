package com.example.accountbook.controller;

import com.example.accountbook.pojo.Disburse;
import com.example.accountbook.pojo.Income;
import com.example.accountbook.pojo.SysUser;
import com.example.accountbook.service.DisburseService;
import com.example.accountbook.service.IncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecordsController {
    @Autowired
    IncomeService incomeService;

    @Autowired
    DisburseService disburseService;

    @SneakyThrows
    @ResponseBody
    @RequestMapping("/addRecord")
    public String addRecord(@RequestBody Map<String,String> requestMap){
        String type=requestMap.get("type");
        String text=requestMap.get("text");
        double amount=Double.parseDouble(requestMap.get("amount"));
        String recordType=requestMap.get("recordType");
        int id=getCurrentUserId();
        int result=0;
        if(recordType.equals("收入"))
            result=addIncomeRecord(id,type,text,amount);
        else result=addDisburseRecord(id,type,text,amount);
        HashMap<String,Object>map=new HashMap<>();
        if(result>0){
            map.put("msg","添加成功");
            map.put("code",200);
        }
        else {
            map.put("msg","添加失败");
            map.put("code",500);
        }
        String str=new ObjectMapper().writeValueAsString(map);
        return str;
    }

    @ResponseBody
    @RequestMapping("/getRecordsById")
    public List<Map<String,Object>> getRecordsById(@RequestBody Map<String,String>requestMap){
        String recordType=requestMap.get("recordType");
        List<Map<String,Object>> resultList=new ArrayList<>();
        int id=getCurrentUserId();
        if(recordType.equals("收入")){
            List<Income> incomeList=incomeService.queryIncomeById(id);
            for(Income income:incomeList){
                resultList.add(income.getInfoMap());
            }
        }
        else{
            List<Disburse> disburseList=disburseService.queryDisburseById(id);
            for (Disburse disburse:disburseList){
                resultList.add(disburse.getInfoMap());
            }
        }
        return resultList;
    }

    @ResponseBody
    @RequestMapping("/getRecordsByType")
    public List<Map<String,Object>> getRecordsByType(@RequestBody Map<String,String>requestMap){
        int id=getCurrentUserId();
        String type=requestMap.get("type");
        String recordType=requestMap.get("recordType");
        List<Map<String,Object>> resultList=new ArrayList<>();
        if(recordType.equals("收入")){
            List<Income> incomeList= incomeService.queryIncomeByType(id,type);
            for(Income income:incomeList){
                resultList.add(income.getInfoMap());
            }
        }
        else{
            List<Disburse> disburseList=disburseService.queryDisburseByType(id,type);
            for(Disburse disburse:disburseList){
                resultList.add(disburse.getInfoMap());
            }
        }
        return resultList;
    }


    @ResponseBody
    @RequestMapping("/getDisburseCounts")
    public List<List<Double>> getDisburseCounts(){
        int id=getCurrentUserId();
        List<List<Double>> resultList=new ArrayList<>();
        List<Double> list=new ArrayList<>();
        String[] disburses={"餐饮","交通","日用品"};
        for(int i=0;i<disburses.length;i++){
            list.add(disburseService.getDaysAmountsByType(id,disburses[i]));
        }
        resultList.add(list);
        list=new ArrayList<>();
        for(int i=0;i<disburses.length;i++){
            list.add(disburseService.getWeeksAmountByType(id,disburses[i]));
        }
        resultList.add(list);
        list=new ArrayList<>();
        for(int i=0;i<disburses.length;i++){
            list.add(disburseService.getMonthsAmountByType(id,disburses[i]));
        }
        resultList.add(list);
        return resultList;
    }

    @ResponseBody
    @RequestMapping("/getIncomeCounts")
    public List<List<Double>> getIncomeCounts(){
        int id=getCurrentUserId();
        List<List<Double>> resultList=new ArrayList<>();
        List<Double> list=new ArrayList<>();
        String[] incomes={"工资","兼职","其他"};
        for(int i=0;i<incomes.length;i++){
            list.add(incomeService.getDaysAmountsByType(id,incomes[i]));
        }
        resultList.add(list);
        list=new ArrayList<>();
        for(int i=0;i<incomes.length;i++){
            list.add(incomeService.getWeeksAmountByType(id,incomes[i]));
        }
        resultList.add(list);
        list=new ArrayList<>();
        for(int i=0;i<incomes.length;i++){
            list.add(incomeService.getMonthsAmountByType(id,incomes[i]));
        }
        resultList.add(list);
        return resultList;
    }

    public int getCurrentUserId(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SysUser user=(SysUser) authentication.getPrincipal();
        return user.getId();
    }

    public int addIncomeRecord(int id,String type,String text,double amount){
        Income income=new Income(id,type,amount,text);
        return incomeService.addIncome(income);
    }

    public int addDisburseRecord(int id,String type,String text,double amount){
        Disburse disburse=new Disburse(id,type,amount,text);
        return disburseService.addDisburse(disburse);
    }
}
