package Exceptions;

public class switchScreenException extends Exception {
	private static final long serialVersionUID = 1L;

	public switchScreenException(String paneName) {
		super("switchScreenException: There is no " + paneName + " in the ScreenController.");
	}
}