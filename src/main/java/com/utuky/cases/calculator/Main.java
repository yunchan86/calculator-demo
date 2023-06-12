package com.utuky.cases.calculator;

import com.utuky.cases.calculator.dto.CalculatorDataObject;
import com.utuky.cases.calculator.operator.SymbolEnums;
import com.utuky.cases.calculator.utils.ExtendCalcUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 22:42:57
 */
public class Main {

    public static void main(String[] args) {
        CalculatorDataObject dataObject = new CalculatorDataObject();
        System.out.println("+++++++++Start++++++++++");
        System.out.println("Please Input mathematical operation(+-*/):");
        Scanner sc=new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()) {
            String value = sc.next();
            if(value.equals("exit")) {
                break;
            }
            if(value.indexOf(SymbolEnums.UNDO.getSymbol())!=-1) {
                ExtendCalcUtil.undo(dataObject,value);
            }
            if(value.indexOf(SymbolEnums.REDO.getSymbol())!=-1) {
                ExtendCalcUtil.redo(dataObject,value);
            }
            sb.append(value);
            System.out.println("value is "+value);
        }
        System.out.println("all "+sb.toString());
        dataObject.setExpression(sb.toString());
        Calculator calculator = new Calculator(dataObject);
        calculator.mathCalc();
//        int a= sc.nextInt();
//        int b= sc.nextInt();
//        System.out.println("a="+a+"  b="+b);
        System.out.println("+++++++++End++++++++++");
    }
}
