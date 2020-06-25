package channelpopularity._exceptions;

/** user-defined exception class - this exception is thrown if input file format is invalid */
public class InvalidFormatException extends Exception {

  public InvalidFormatException() {
    super("InvalidFormatException : Please Ensure Input Line Format is Valid");
  }

  public InvalidFormatException(String message) {
    System.out.println(
        "InvalidFormatException : [ " + message + " ] Please Ensure Input Line Format is Valid");
  }
}
