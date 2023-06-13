package com.utuky.cases.calculator.utils;

import com.utuky.cases.calculator.dto.CalculatorDataObject;
import com.utuky.cases.calculator.operator.CommandCalc;
import com.utuky.cases.calculator.operator.OperatorEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 21:48:20
 */
public class CalcUtil {

    public static List<String> twoTreeMiddle(String str) {//把输入的字符串转换成中缀表达式。存入list中
        int index = 0;
        List<String> list = new ArrayList<String>();
        do{
            char ch = str.charAt(index);
            if("+-*/^!logsct()".indexOf(str.charAt(index)) >= 0){
                //是操作符，直接添加至list中
                index ++;
                list.add(ch+"");
            }else if (str.charAt(index) == 'e' || str.charAt(index) == 'p'){
                index ++;
                list.add(ch+"");
            } else if("0123456789".indexOf(str.charAt(index)) >= 0){
                //是数字,判断多位数的情况
                String str1 = "";
                while (index < str.length() && "0123456789.".indexOf(str.charAt(index)) >= 0){
                    str1 += str.charAt(index);
                    index ++;
                }
                list.add(str1);

            }
        }while (index < str.length());
        return list;
    }

    public static List<String> twoTreeSuffixFromMiddle(List<String> list){//中缀表达式转换称后缀表达式
        Stack<String> symbol = new Stack<>();
        List<String> calcList = new ArrayList<>();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (NumberUtil.isNumber(list.get(i))){
                    calcList.add(list.get(i));
                } else if (list.get(i).charAt(0) == '('){
                    symbol.push(list.get(i));
                } else if (NumberUtil.isOperator(list.get(i)) && list.get(i).charAt(0) != '('){
                    if (symbol.isEmpty()){
                        symbol.push(list.get(i));
                    } else {//符栈不为空
                        if (list.get(i).charAt(0) != ')'){
                            if (NumberUtil.priority(symbol.peek()) <= NumberUtil.priority(list.get(i))){
                                //入栈
                                symbol.push(list.get(i));
                            } else {//出栈
                                while (!symbol.isEmpty() && !"(".equals(symbol.peek())){
                                    if(NumberUtil.priority(list.get(i)) <= NumberUtil.priority(symbol.peek())){
                                        calcList.add(symbol.pop());
                                    }
                                }
                                if (symbol.isEmpty() || symbol.peek().charAt(0) == '('){
                                    symbol.push(list.get(i));
                                }
                            }
                        } else if (list.get(i).charAt(0) == ')'){
                            while (symbol.peek().charAt(0) != '('){
                                calcList.add(symbol.pop());
                            }
                            symbol.pop();
                        }
                    }
                }
            }
            while (!symbol.isEmpty()){
                calcList.add(symbol.pop());
            }
        } else {
            //jTextField1.setText("");
        }
        return calcList;
    }

    public static double math(List<String> calcList, CalculatorDataObject dataObject) {//通过后缀表达式进行计算
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < calcList.size(); i++) {
            String indexValue = calcList.get(i);
            if (NumberUtil.isNumber(indexValue)) {
                if (calcList.get(i).charAt(0) == 'e'){
                    stack.push(String.valueOf(Math.E));
                } else if (calcList.get(i).charAt(0) == 'p'){
                    stack.push(String.valueOf(Math.PI));
                } else {
                    stack.push(calcList.get(i));
                }
            } else if (NumberUtil.isOperator(indexValue)){
                double res = 0;
                OperatorEnums operatorEnums = OperatorEnums.getOperatorEnums(indexValue);
                if(operatorEnums!=null) {
                    CommandCalc commandCalc = operatorEnums.getCommand();
                    res = commandCalc.calc(stack).doubleValue();
                }
                if (calcList.get(i).equals("!")) {
                    double num1 = Double.parseDouble(stack.pop());
                    if (num1 == 0 || num1 == 1){
                        res = 1;
                    } else if (num1 == (int)num1 && num1 > 1){
                        int d = 1;
                        for (int j = (int)num1; j >0; j--) {
                            d *= j;
                        }
                        res = d;
                    } else {
                        System.err.println("阶乘必须为自然数");
                        dataObject.setIndexYN(1);
                    }
                } else if (calcList.get(i).equals("g")) {
                    double num1 = Double.parseDouble(stack.pop());
                    res = Math.sqrt(num1);
                } else if (calcList.get(i).equals("l")) {
                    double num1 = Double.parseDouble(stack.pop());
                    if (num1 > 0){
                        res = Math.log(num1);
                    } else {
                        System.err.println("ln的x必须大于0");
                        dataObject.setIndexYN(1);
                    }
                } else if (calcList.get(i).equals("o")) {
                    double num1 = Double.parseDouble(stack.pop());
                    if (num1 > 0){
                        res = Math.log(num1) / Math.log(2);
                    } else {
                        System.err.println("log的x必须大于0");
                        dataObject.setIndexYN(1);
                    }
                } else if (calcList.get(i).equals("s")) {
                    double num1 = Double.parseDouble(stack.pop());
                    res = Math.sin(num1);
                } else if (calcList.get(i).equals("c")) {
                    double num1 = Double.parseDouble(stack.pop());
                    res = Math.cos(num1);
                } else if (calcList.get(i).equals("t")) {
                    double num1 = Double.parseDouble(stack.pop());
                    if (Math.cos(num1) != 0){
                        res = Math.tan(num1);
                    } else {
                        System.err.println("tan的x不能为+-(π/2 + kπ)");
                        dataObject.setIndexYN(1);
                    }
                }
                stack.push("" + res);
            }
        }
        if (dataObject.getIndexYN() == 0){
            if (!stack.isEmpty()){
                return Double.parseDouble(stack.pop());
            } else {
                return 0;
            }
        } else {
            return -999999;
        }
    }

    public static void calc(CalculatorDataObject dataObject){//进行计算，并且判断括号是否匹配
        String khao = "";
        int leftkh = 0;
        int rightkh = 0;
        int m = 0;
        String str = dataObject.getExpression();
        //判断括号是否成对
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '('){
                khao += '(';
                leftkh++;
            }
            if (str.charAt(i) == ')'){
                khao += ')';
                rightkh++;
            }
        }
        if (leftkh != rightkh){
            System.err.println("输入错误！括号不匹配");
            dataObject.setIndexYN(1);
        }
        if ((leftkh == 0 && rightkh == 0) || ((leftkh == rightkh && leftkh > 0) && khao.charAt(0) == '(' && khao.charAt(khao.length() - 1) == ')')){
            if (dataObject.getIndexYN()  == 0){
                List<String> middleList = twoTreeMiddle(str);
                List<String> suffixList = twoTreeSuffixFromMiddle(middleList);
                if (dataObject.getIndexYN() == 0){
                    double total = math(suffixList,dataObject);
                    dataObject.setTotal(total);
                    if (total == -999999){
                        System.err.println("计算错误："+-999999);
                    } else {
                        System.out.println("计算的结果为："+ String.valueOf(total));
                    }
                }
            }
        } else {
            System.err.println("输入错误！括号不匹配");
            dataObject.setIndexYN(1);
        }
    }

    /**
     * 校验表达式，判断输入是否错误
     * @param dataObject
     */
    public static void validate(CalculatorDataObject dataObject){
        String str = dataObject.getExpression();
        int i = 0;
        if (str.length() == 0){
        }
        if (str.length() == 1){
            //当只有一位字符时，只能是“0123456789ep”中的一个
            if ("0123456789ep".indexOf(str.charAt(0)) == -1){
                System.err.println("输入错误！");
                dataObject.setIndexYN(1);
            }
        }
        if (str.length() > 1){
            for (i = 0; i < str.length() - 1; i++) {
                //1.第一个字符只能为"losctg(0123456789ep"中的一个
                if ("losctg(0123456789ep".indexOf(str.charAt(0)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //2.“+-*/”后面只能是"0123456789losctg(ep"中的一个
                if ("+-*/".indexOf(str.charAt(i)) >= 0 && "0123456789losctg(ep".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //3."."后面只能是“0123456789”中的一个
                if (str.charAt(i) == '.' && "0123456789".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //4."!"后面只能是“+-*/^)”中的一个
                if (str.charAt(i) == '!' && "+-*/^)".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //5."losctg"后面只能是“0123456789(ep”中的一个
                if ("losctg".indexOf(str.charAt(i)) >= 0 && "0123456789(ep".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //6."0"的判断操作
                if (str.charAt(0) == '0' && str.charAt(1) == '0'){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                if (i >= 1 && str.charAt(i) == '0'){
                    //&& str.charAt(0) == '0' && str.charAt(1) == '0'
                    int m = i;
                    int n = i;
                    int is = 0;
                    //1.当0的上一个字符不为"0123456789."时，后一位只能是“+-*/.!^)”中的一个
                    if ("0123456789.".indexOf(str.charAt(m - 1)) == -1 && "+-*/.!^)".indexOf(str.charAt(i + 1)) == -1){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }
                    //2.如果0的上一位为“.”,则后一位只能是“0123456789+-*/.^)”中的一个
                    if (str.charAt(m - 1) == '.' && "0123456789+-*/.^)".indexOf(str.charAt(i + 1)) == -1){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }
                    n -= 1;
                    while (n > 0){
                        if ("(+-*/^glosct".indexOf(str.charAt(n)) >= 0){
                            break;
                        }
                        if (str.charAt(n) == '.'){
                            is++;
                        }
                        n--;
                    }

                    //3.如果0到上一个运算符之间没有“.”的话，整数位第一个只能是“123456789”，
                    //  且后一位只能是“0123456789+-*/.!^)”中的一个。
                    if ((is == 0 && str.charAt(n) == '0') || "0123456789+-*/.!^)".indexOf(str.charAt(i + 1)) == -1){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }
                    //4.如果0到上一个运算符之间有一个“.”的话,则后一位只能是“0123456789+-*/.^)”中的一个
                    if (is == 1 && "0123456789+-*/.^)".indexOf(str.charAt(i + 1)) == -1){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }
                    if (is > 1){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }

                }
                //7."123456789"后面只能是“0123456789+-*/.!^)”中的一个
                if ("123456789".indexOf(str.charAt(i)) >= 0 && "0123456789+-*/.!^)".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //8."("后面只能是“0123456789locstg()ep”中的一个
                if (str.charAt(i) == '(' && "0123456789locstg()ep".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //9.")"后面只能是“+-*/!^)”中的一个
                if (str.charAt(i) == ')' && "+-*/!^)".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //10.最后一位字符只能是“0123456789!)ep”中的一个
                if ("0123456789!)ep".indexOf(str.charAt(str.length() - 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
                //12.不能有多个“.”
                if (i > 2 && str.charAt(i) == '.'){
                    int n = i - 1;
                    int is = 0;
                    while (n > 0){
                        if ("(+-*/^glosct".indexOf(str.charAt(n)) >= 0){
                            break;
                        }
                        if (str.charAt(n) == '.'){
                            is++;
                        }
                        n--;
                    }
                    if (is > 0){
                        System.err.println("输入错误！");
                        dataObject.setIndexYN(1);
                    }
                }
                //13."ep"后面只能是“+-*/^)”中的一个
                if ("ep".indexOf(str.charAt(i)) >= 0 && "+-*/^)".indexOf(str.charAt(i + 1)) == -1){
                    System.err.println("输入错误！");
                    dataObject.setIndexYN(1);
                }
            }
        }
    }
}
