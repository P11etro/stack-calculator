package ComplexCalculatorException;

/**
 * Exception thrown when user asks for an operation that requires more
 * operands than are available.
 */
public class NotEnoughDataException extends ComplexCalculatorException {

    /**
     *
     */
    public NotEnoughDataException() {
    }

    /**
     *
     * @param string
     */
    public NotEnoughDataException(String string) {
        super(string);
    }
    
}
