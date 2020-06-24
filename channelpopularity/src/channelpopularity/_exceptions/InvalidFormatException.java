package channelpopularity._exceptions;

public class InvalidFormatException extends Exception {

  public InvalidFormatException() {
    super("InvalidFormatException : Please Ensure Input Line Format is Valid");
  }

  public InvalidFormatException(String message) {
    System.out.println(
        "InvalidFormatException : [ " + message + " ] Please Ensure Input Line Format is Valid");
  }
}
