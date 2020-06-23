package channelpopularity.driver;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.helper.LineParser;
import channelpopularity.state.StateName;
import channelpopularity.util.FileProcessor;
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

    List<StateName> stateNames = Arrays.asList(StateName.values());
    ContextI channel = new ChannelContext(stateNames);

    FileProcessor fp = new FileProcessor(args[0]);
    LineParser lp = new LineParser(fp, channel);

    lp.processFile();
  }
}
