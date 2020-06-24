package channelpopularity._exceptions;

public class InvalidOperationException extends Exception {

  public InvalidOperationException() {
    super("InvalidOperationException: Operation Not Permitted");
  }

  public InvalidOperationException(String message) {
    System.out.println("InvalidOperationException: " + message);
  }
}
