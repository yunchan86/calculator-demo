package com.utuky.cases.calculator;

import com.utuky.cases.calculator.dto.CalculatorDataObject;
import com.utuky.cases.calculator.operator.SymbolEnums;
import com.utuky.cases.calculator.utils.ExtendCalcUtil;
import com.utuky.cases.calculator.utils.NumberUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 22:42:57
 */
public class Main {

    public static void main(String[] args) {
        CalculatorDataObject dataObject = new CalculatorDataObject();
        Calculator calculator = new Calculator(dataObject);
        System.out.println("+++++++++Start++++++++++");
        System.out.println("Please Input mathematical operation(+、-、*、/、()、=、undo、redo) or exit:");
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()) {
            String value = sc.next();
            boolean isCalc = false;
            if(value.equals("exit")) {
                break;
            }
            if(!NumberUtil.supportInput(value)) {
                System.err.println("错误的输入");
            }
            if(value.indexOf(SymbolEnums.UNDO.getSymbol())!=-1) {
                ExtendCalcUtil.undo(dataObject,value);
            }
            if(value.indexOf(SymbolEnums.REDO.getSymbol())!=-1) {
                ExtendCalcUtil.redo(dataObject,value);
            }
            if(value.lastIndexOf("=")!=-1) {
                isCalc = true;
            }
            if(NumberUtil.supportInputExpression(value)) {
                dataObject.setExpression((dataObject.getExpression()==null ? "":dataObject.getExpression()) + value.replaceAll("=",""));
                ExtendCalcUtil.resetInputExpression(dataObject);
            }
            System.out.println("表达式为： "+dataObject.getExpression());
            if(isCalc) {//计算结果，同时清除表达式
                inputCalc(calculator,dataObject,true);
            }
        }
        ExtendCalcUtil.resetInputExpression(dataObject);
        calculator.mathCalc();
        System.out.println("+++++++++End++++++++++");
    }

    private static void inputCalc(Calculator calculator,CalculatorDataObject dataObject,boolean equalMark) {
        calculator.mathCalc();//计算
        if(equalMark) {
            double total = dataObject.getTotal().doubleValue();
            dataObject.clearEmpty();
            dataObject.setExpression(""+total);
            dataObject.setInputExpression(dataObject.getExpression());
        }
    }
}
