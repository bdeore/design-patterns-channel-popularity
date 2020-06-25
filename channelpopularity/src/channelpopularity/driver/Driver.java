package channelpopularity.driver;

import channelpopularity._exceptions.EmptyInputFileException;
import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.helper.LineParser;
import channelpopularity.state.StateName;
import channelpopularity.util.FileProcessor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.List;

/** @author Bhagwan Deore */
public class Driver {
  private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

  public static void main(String[] args) throws Exception {

    /*
     * As the build.xml specifies the arguments as input,output or metrics, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
      System.err.printf(
          "Error: Incorrect number of arguments. Program accepts %d arguments.",
          REQUIRED_NUMBER_OF_CMDLINE_ARGS);
      System.exit(0);
    }

    try {
      List<StateName> stateNames = Arrays.asList(StateName.values());
      ContextI channel = new ChannelContext(stateNames);

      FileProcessor fp = new FileProcessor(args[0]);
      LineParser lp = new LineParser(fp, channel);

      lp.processFile();

      /*
       * Results object provides uniform interface through write() method for writing output to
       * files as well as standard out. creation of results object is managed by channel to ensure
       * that each channel has its own separate instance of the object.
       *
       *  write() method without any parameters prints output and metrics to the console
       *  write(output_file_name) writes output and metrics to specified
       *  files.
       *
       */
      channel.write();
      channel.write(args[1]);

    } catch (InvalidPathException
        | FileNotFoundException
        | SecurityException
        | ArithmeticException
        | EmptyInputFileException e) {
      System.out.println(e);
      System.out.println("(Class Driver) Terminating Program");
      System.exit(1);
      // e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IOException occurred in FileProcessor class\n" + e);
      System.exit(1);
      // e.printStackTrace();
    }
  }

  /**
   * toString() method - helpful for debugging
   *
   * @return name of the class
   */
  @Override
  public String toString() {
    return "Driver Object";
  }
}
