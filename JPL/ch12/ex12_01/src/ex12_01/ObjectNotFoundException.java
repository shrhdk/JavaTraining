package ex12_01;

public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
	}

	public ObjectNotFoundException(String details) {
		super(details);
	}
	
	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ObjectNotFoundException(String details, Throwable cause) {
		super(details, cause);
	}
}
