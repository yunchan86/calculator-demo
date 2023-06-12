package com.utuky.cases.calculator.operator;

/**
 * @description:
 * @author: chenhuangyun
 * @create: 2023-06-12 16:30:08
 */
public enum OperatorEnums {

    ADD(SymbolEnums.ADD.getSymbol(),new AddCalc())
    ,MINUS(SymbolEnums.MINUS.getSymbol(),new MinusCalc())
    ,MULTIPLU(SymbolEnums.MULTIPLU.getSymbol(),new MultiplyCalc())
    ,DEVIDE(SymbolEnums.DEVIDE.getSymbol(),new DevideCalc())
    ;

    private OperatorEnums(String operator,CommandCalc command) {
        this.operator = operator;
        this.command = command;
    }

    private String operator;
    private CommandCalc command;

    public static OperatorEnums getOperatorEnums(String operator) {
        OperatorEnums result = null;
        for(OperatorEnums operatorEnums : OperatorEnums.values()) {
            if(operatorEnums.operator.equalsIgnoreCase(operator)) {
                result = operatorEnums;
                break;
            }
        }
        return result;
    }

    public String getOperator() {
        return operator;
    }

    public CommandCalc getCommand() {
        return command;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setCommand(CommandCalc command) {
        this.command = command;
    }
}
