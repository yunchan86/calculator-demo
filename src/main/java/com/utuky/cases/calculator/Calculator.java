package com.utuky.cases.calculator;

import com.utuky.cases.calculator.dto.CalculatorDataObject;
import com.utuky.cases.calculator.utils.CalcUtil;

import java.math.BigDecimal;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-13 00:07:01
 */
public class Calculator {

    private CalculatorDataObject dataObject;

    public Calculator(CalculatorDataObject dataObject) {
        this.dataObject = dataObject;
    }

    public double mathCalc() {
        CalcUtil.validate(dataObject);
        if(dataObject.getIndexYN()==0) {
            CalcUtil.calc(dataObject);
            return dataObject.getTotal().doubleValue();
        }
        return -999999999;
    }



}
