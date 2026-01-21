package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Transaction {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private final String name;
    Transaction(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static Map<String, String> type(){
        Map<String, String> transactions = new LinkedHashMap<>();
        for(Transaction item : Transaction.values()){
            transactions.put(item.toString(), item.name);
        }
        return transactions;
    }
}
