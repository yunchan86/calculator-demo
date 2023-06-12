package com.utuky.cases.calculator.utils;

import com.utuky.cases.calculator.dto.CalculatorDataObject;
import com.utuky.cases.calculator.operator.SymbolEnums;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 23:38:50
 */
public class ExtendCalcUtil {

    public static void undo(CalculatorDataObject dataObject,String currentInput) {
        if(dataObject.getInputExpression().lastIndexOf(SymbolEnums.UNDO.getSymbol())!=-1) {
            int length = dataObject.getInputExpression().length();

        }
        //dataObject.getInputExpression().
    }

    public static void redo(CalculatorDataObject dataObject,String currentInput) {
        if(dataObject.getInputExpression().indexOf(dataObject.getExpression())!=-1) {
            int length = dataObject.getInputExpression().length();
            String suffixStr = dataObject.getInputExpression().substring(length);
            int suffixLength = suffixStr.length();
            char operator = suffixStr.charAt(0);
            String appand = new String(new char[]{operator});
            for(int i=1;i<suffixLength;i++) {
                if(NumberUtil.isOperator(suffixStr.charAt(i))){
                    break;
                }else {
                    appand += new Character(suffixStr.charAt(i)).toString();
                }
            }
            dataObject.setExpression(dataObject.getExpression()+appand);
            System.out.println(appand);
        }else {
            dataObject.setInputExpression(dataObject.getInputExpression());
        }
    }
}
