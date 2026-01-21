package com.javaweb.enums;


import java.util.*;

public enum TypeCode {
    TANG_TRET("Tầng trẹt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    //định nghĩa bên trong enum (cái này có thể thay đổi vd private final int thoigian;)
    private final String name;


    TypeCode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static Map<String, String> type(){
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for(TypeCode item : TypeCode.values()){
            typeCodes.put(item.toString(), item.name);
        }
        return typeCodes;
    }
}
