package com.example.accountbook.dao;

import com.example.accountbook.dto.DisburseDTO;
import com.example.accountbook.dto.IncomeDTO;
import com.example.accountbook.dto.UserDTO;
import com.example.accountbook.pojo.Role;
import com.example.accountbook.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    SysUser getUserByName(String username);
    List<Role> getRolesById(int id);
    int updatePassword(String username,String password);
    int addCommanUser(String username,String password);
    int addUser_Role(int uid,int rid);
    List<Map<String,Integer>> getAllUserNameAndId();
    UserDTO getUserAccountInfo(int id);
    List<DisburseDTO> getDisburseDTOS(int id);
    List<IncomeDTO> getIncomeDTOS(int id);
}
