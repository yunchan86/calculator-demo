package com.utuky.cases.calculator.utils;

import com.utuky.cases.calculator.operator.SymbolEnums;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 16:06:56
 */
public class NumberUtil {

    /**
     * 判断是否为操作符
     * @param op
     * @return
     */
    public static boolean isOperator(String op){//判断是否为操作符
        if ("0123456789.ep".indexOf(op.charAt(0)) == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperator(char op){//判断是否为操作符
        if ("0123456789.ep".indexOf(op) == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否为计算数
     * @param num
     * @return
     */
    public static boolean isNumber(String num){
        if ("0123456789.ep".indexOf(num.charAt(0)) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber(char num){
        if ("0123456789ep".indexOf(num) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断计算的优先级
     * @param f
     * @return
     */
    public static int priority(String f){//判断操作符的优先级
        int result = 0;
        switch(f) {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            case "^":
                result = 3;
                break;
            case "!":
            case "g":
            case "l":
            case "s":
            case "o":
            case "c":
            case "t":
                result = 4;
                break;

        }
        return result;
    }

    public static boolean supportInput(String input) {
        if(input != null && !input.equalsIgnoreCase("")) {
            if(input.equalsIgnoreCase(SymbolEnums.REDO.getSymbol())
             || input.equalsIgnoreCase(SymbolEnums.UNDO.getSymbol())) {
                return true;
            }
            String regx = "0123456789.+-*/()=";
            int length = input.length();
            for(int i=0;i<length;i++) {
                if(regx.indexOf(input.charAt(i))==-1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean supportInputExpression(String input) {
        if(input != null && !input.equalsIgnoreCase("")) {
            String regx = "0123456789.+-*/()=";
            int length = input.length();
            for(int i=0;i<length;i++) {
                if(regx.indexOf(input.charAt(i))==-1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
