package com.example.demo.enjoy.concurrent.bq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/5
 */
@Data
@AllArgsConstructor
public class Order {
    private String orderNum;
    private BigDecimal orderMoney;
}
