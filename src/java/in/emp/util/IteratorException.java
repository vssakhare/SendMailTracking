package in.emp.util;

public class IteratorException extends Exception
{
	public IteratorException() {
		super("Iterator Exception.");
	}

	public IteratorException(String msg) {
		super("Iterator Exception." + msg);
	}
}