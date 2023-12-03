package ComplexCalculatorOperation;

import ComplexCalculatorException.NotEnoughDataException;
import java.util.ArrayDeque;
import ComplexCalculator.Complex;

public class ArithmeticOperation extends Operation {

    public ArithmeticOperation (ArrayDeque stack) {
        super(stack);
    }

    @Override
    public void operationInterpreter(String input){
       if(input.equals("+"))
            sum();
        else if(input.equals("-"))
            sub();
        else if(input.equals("*"))
            product();
        else if(input.equals("/"))
            division();
        else if(input.equals("sqrt"))
            sqrt();
        else if(input.equals("+-"))
            inversion();
    }
    
    public void sum(){
        if(stack.size()<2)
            throw new NotEnoughDataException();
        Complex n1 = stack.pop();
        Complex n2 = stack.pop();
        stack.push(n2.sum(n1));
    }
    
    public void sub(){
        if(stack.size()<2)
            throw new NotEnoughDataException();
        Complex n1 = stack.pop();
        Complex n2 = stack.pop();
        stack.push(n2.sub(n1));
    }
    
    public void product(){
        if(stack.size()<2)
            throw new NotEnoughDataException();
        Complex n1 = stack.pop();
        Complex n2 = stack.pop();
        stack.push(n2.product(n1));
    }
    
    public void division(){
        if(stack.size()<2)
            throw new NotEnoughDataException();
        Complex n1 = stack.pop();
        Complex n2 = stack.pop();
        
        stack.push(n2.division(n1));
    }
    
    public void sqrt(){
        if(stack.size()<1)
            throw new NotEnoughDataException();
        Complex n = stack.pop();
        stack.push(n.sqrt());
    }
    
    public void inversion(){
        if(stack.size()<1)
            throw new NotEnoughDataException();
        Complex n = stack.pop();
        stack.push(n.negate());
    }
}
