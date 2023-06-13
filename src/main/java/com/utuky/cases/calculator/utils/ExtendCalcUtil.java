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
        if(currentInput.equalsIgnoreCase(SymbolEnums.UNDO.getSymbol())) {
            if(dataObject.getExpression()==null) {
                return;
            }
            int length = dataObject.getExpression().length();
            if(length>0){
                String newExpression = dataObject.getExpression().substring(0,length-1);
                dataObject.setExpression(newExpression);
            }
        }
    }

    public static void redo(CalculatorDataObject dataObject,String currentInput) {
        if(dataObject.getInputExpression()==null ||
                dataObject.getExpression()==null) {
            return;
        }
        if(dataObject.getInputExpression().indexOf(dataObject.getExpression())!=-1
            && currentInput.equalsIgnoreCase(SymbolEnums.REDO.getSymbol())) {
            int length = dataObject.getExpression().length();
            int inputLength = dataObject.getInputExpression().length();
            if(inputLength>length) {
                String newExpression = dataObject.getInputExpression().substring(0,length+1);
                dataObject.setExpression(newExpression);
            }
        }else {
            dataObject.setInputExpression(dataObject.getInputExpression());
        }
    }

    public static void resetInputExpression(CalculatorDataObject dataObject) {
        if(dataObject.getInputExpression() == null
            || dataObject.getInputExpression().indexOf(dataObject.getExpression())==-1) {
            dataObject.setInputExpression(dataObject.getExpression());
        }
    }
}
