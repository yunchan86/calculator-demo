package com.utuky.cases.calculator.dto;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 22:14:05
 */
public class CalculatorDataObject {
    /**
     * 计算的总数
     */
    private Number total;
    /**
     * 计算表达式
     */
    private String expression;

    private String inputExpression;

    private int indexYN = 0;

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getIndexYN() {
        return indexYN;
    }

    public void setIndexYN(int indexYN) {
        this.indexYN = indexYN;
    }

    public void clearEmpty() {
        this.setTotal(null);
        this.indexYN = 0;
        this.expression = "";
    }

    public String getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }
}
