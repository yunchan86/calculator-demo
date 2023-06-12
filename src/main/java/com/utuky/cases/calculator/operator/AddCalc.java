package com.utuky.cases.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-13 00:20:47
 */
public class AddCalc extends CommandCalc{

    public BigDecimal calc(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    public BigDecimal calc(Stack<String> stack) {
        String numStr1 = stack.pop();
        String numStr2 = stack.pop();
        BigDecimal num1 = new BigDecimal(numStr1);
        BigDecimal num2 = new BigDecimal(numStr2);
        return calc(num1,num2);
    }
}
