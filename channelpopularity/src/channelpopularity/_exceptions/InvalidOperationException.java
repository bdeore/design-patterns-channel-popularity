package channelpopularity._exceptions;

/** user-defined exception class - this exception is thrown if requested operation is invalid */
public class InvalidOperationException extends Exception {

  public InvalidOperationException() {
    super("InvalidOperationException: Operation Not Permitted");
  }

  public InvalidOperationException(String message) {
    System.out.println("InvalidOperationException: " + message);
  }
}
