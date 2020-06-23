package channelpopularity.util;

import java.nio.file.InvalidPathException;
import java.util.Vector;

/** interface for standard out IO */
public interface StdoutDisplayInterface {
  Vector<String> resultBuffer = null;

  void write() throws ArithmeticException, InvalidPathException;
}
