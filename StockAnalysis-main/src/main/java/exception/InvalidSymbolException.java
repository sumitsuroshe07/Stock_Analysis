package exception;

public class InvalidSymbolException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSymbolException(String message) {
        super(message);
    }
}

