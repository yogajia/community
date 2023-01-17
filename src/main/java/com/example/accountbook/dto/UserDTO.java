package com.example.accountbook.dto;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private Integer id;
    private String username;
    List<DisburseDTO> disburseDTOList;
    List<IncomeDTO> incomeDTOList;
}
