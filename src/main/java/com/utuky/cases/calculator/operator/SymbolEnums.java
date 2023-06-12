package com.utuky.cases.calculator.operator;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 22:19:34
 */
public enum SymbolEnums {
    ADD("+")
    ,MINUS("-")
    ,MULTIPLU("*")
    ,DEVIDE("/")
    ,UNDO("undo")
    ,REDO("redo")
    ;

    private String symbol;

    private SymbolEnums(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
