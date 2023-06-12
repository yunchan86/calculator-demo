package com.utuky.cases.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-13 00:12:34
 */
public abstract class CommandCalc {

    public abstract BigDecimal calc(BigDecimal num1,BigDecimal num2);

    public BigDecimal calc(BigDecimal num) {
        return num;
    }

    public abstract BigDecimal calc(Stack<String> stack) ;

}
