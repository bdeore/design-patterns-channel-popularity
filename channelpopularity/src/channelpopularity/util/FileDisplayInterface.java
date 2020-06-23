package channelpopularity.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Vector;

/** Interface for File IO */
public interface FileDisplayInterface {
  Vector<String> resultBuffer = null;

  void write(String output_filename) throws ArithmeticException, InvalidPathException, IOException;
}
