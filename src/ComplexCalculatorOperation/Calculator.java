package ComplexCalculatorOperation;

import java.util.ArrayDeque;
import ComplexCalculator.Complex;

public class Calculator {
    private final ArrayDeque<Complex> stack;
    private final Operation arithmeticOperation;
    private final Operation stackOperation;
    private final Operation variableOperation;
    
    
    public Calculator(){
        stack = new ArrayDeque<>();
        arithmeticOperation = new ArithmeticOperation(stack);
        stackOperation = new StackOperation(stack);
        variableOperation = new VariableOperation(stack);
    }
    
    public void interpreter(String input){
        boolean operation = false;
        boolean stackoperation = false;
        boolean variable = false;
        
        String[] variableOperations = {"+","-","<",">","varMap"};
        String[] stackOperations = {"clear", "drop", "dup", "swap", "over"};
        String[] operations = {"+","-","*","/","+-","sqrt"};
        
        if (input.matches(".*[a-z]") & input.length() == 2 | input.equals("varMap")) {
            for (String op : variableOperations) {
                if (input.contains(op)) {
                    variable = true;
                }
            }
        }
        
        if (!variable) {
            for (String op : operations){
                if (input.contains(op) && input.length() == 1)
                    operation = true;
            }
            if (input.equals("+-")||input.equals("sqrt"))
                operation = true;
        }
        
        for (String op:stackOperations) {
            if (input.contains(op)) {
                stackoperation = true;
            }
        }
        
        if (variable)
            variableOperation.operationInterpreter(input);
        else if (operation)
            arithmeticOperation.operationInterpreter(input);
        else if (stackoperation)
            stackOperation.operationInterpreter(input);
        else {
            stack.push(new Complex(input));
        }
    }
        
    public void stampaStack(){
        System.out.println("Stack:");
        stack.forEach(e -> {
            System.out.println(e);
        });
    }
}