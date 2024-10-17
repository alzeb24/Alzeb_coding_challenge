package exceptions;

public class NullReferenceException extends Exception {
	public NullReferenceException(String Name) {
        super("Name or age can not be null");
    }

}
