package com.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
public class Bill implements Serializable {

    private String orderNo;
    private String type;
    private BigDecimal amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
