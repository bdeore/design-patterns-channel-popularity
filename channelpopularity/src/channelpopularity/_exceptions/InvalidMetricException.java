package channelpopularity._exceptions;

/** user-defined exception class - this exception is thrown if metric information is invalid */
public class InvalidMetricException extends Exception {

  public InvalidMetricException() {
    super("InvalidMetricException : Please Ensure Input Metrics are Valid");
  }

  public InvalidMetricException(String message) {
    System.out.println(
        "InvalidMetricException : [ " + message + " ] Please Ensure Input Metrics are Valid");
  }
}
