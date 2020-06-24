package channelpopularity.util;

import channelpopularity._exceptions.EmptyInputFileException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

/** Interface for File IO */
public interface FileDisplayInterface {
  void write(String output_filename)
      throws ArithmeticException, InvalidPathException, IOException, EmptyInputFileException;
}
