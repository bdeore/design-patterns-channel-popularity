package channelpopularity.helper;

import channelpopularity._exceptions.EmptyInputFileException;
import channelpopularity._exceptions.InvalidFormatException;
import channelpopularity._exceptions.InvalidMetricException;
import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity._exceptions.InvalidWordException;
import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.util.FileProcessor;
import java.io.IOException;

/**
 * Helper class to process input file and perform input validations before passing on the control
 * channel context class. uses the instance of file processor and channel class.
 */
public class LineParser {

  private final ChannelContext channel;
  private final FileProcessor fp;

  public LineParser(FileProcessor fp, ContextI channel) {
    this.fp = fp;
    this.channel = (ChannelContext) channel;
  }

  /**
   * processFile method uses file processor object to read in the line. validates format of the line
   * using validateLineFormat() method. switch control statement is used to pass on the control to
   * separate handlers for each operation rather than creating one giant monolithic function.
   */
  public void processFile() {
    int count = 0;
    try {
      String line = fp.poll();
      String delimiters = "[_]{2}|[:,=\\[\\]]+";
      while (line != null) {
        String[] tokens = line.split(delimiters);
        count++;
        validateLineFormat(tokens, count);
        switch (tokens[0]) {
          case "ADD_VIDEO":
            processNewVideo(tokens);
            break;
          case "REMOVE_VIDEO":
            removeVideo(tokens);
            break;
          case "METRICS":
            processMetrics(tokens);
            break;
          case "AD_REQUEST":
            processAdRequest(tokens);
            break;
          default:
            throw new InvalidWordException(
                "[ Line Number "
                    + count
                    + " ] "
                    + ((tokens[0].length() > 0)
                        ? "(" + tokens[0] + ")"
                        : "(Empty Line / Invalid First Token)")
                    + " Please Ensure Input File contains Valid Lines");
        }
        line = fp.poll();
      }
      if (count == 0) throw new EmptyInputFileException();
    } catch (IOException
        | InvalidWordException
        | NumberFormatException
        | InvalidMetricException
        | NullPointerException
        | InvalidFormatException
        | EmptyInputFileException
        | InvalidOperationException e) {
      System.out.println(e);
      System.out.println("(processFile method) Terminating Program");
      System.exit(1);
    }
  }

  /**
   * creates a new video object and passes it on to the context class to add to the channel
   *
   * @param tokens array of tokens in line
   * @throws InvalidOperationException user defined exception for invalid operation
   */
  void processNewVideo(String[] tokens) throws InvalidOperationException {
    Video newVideo = new Video(tokens[1]);
    channel.addVideo(newVideo);
  }

  /**
   * creates a new video object and passes it on to the context class to remove
   *
   * @param tokens array of tokens in line
   * @throws InvalidOperationException user defined exception for invalid operation
   */
  void removeVideo(String[] tokens) throws InvalidOperationException {
    Video video = new Video(tokens[1]);
    channel.removeVideo(video);
  }

  /**
   * validates metrics information is correct and passes it on to be added to channel
   *
   * @param tokens array of tokens in line
   * @throws NumberFormatException exception thrown by Integer.parseInt
   * @throws InvalidMetricException user defined exception for invalid metrics information
   * @throws InvalidOperationException user defined exception for invalid operation
   */
  void processMetrics(String[] tokens)
      throws NumberFormatException, InvalidMetricException, InvalidOperationException {
    if (channel.getVideos().containsKey(tokens[1])) {
      int views = Integer.parseInt(tokens[3]);
      int likes = Integer.parseInt(tokens[5]);
      int dislikes = Integer.parseInt(tokens[7]);

      if (views >= 0) {
        Video vid = new Video(tokens[1]);
        vid.setViews(views);
        vid.setLikes(likes);
        vid.setDislikes(dislikes);
        channel.addMetrics(vid);
      } else {
        throw new InvalidMetricException(tokens[3]);
      }
    } else {
      throw new InvalidOperationException(
          "(Operation METRICS) Video " + tokens[1] + " Does Not Exist");
    }
  }

  /**
   * validates ad length and passes on control to Context class
   *
   * @param tokens array of tokens in line
   * @throws InvalidMetricException user defined exception for invalid metrics information
   * @throws InvalidOperationException user defined exception for invalid operation
   */
  void processAdRequest(String[] tokens) throws InvalidMetricException, InvalidOperationException {
    if (channel.getVideos().containsKey(tokens[1])) {
      int length = Integer.parseInt(tokens[3]);
      if (length > 0) channel.processAdRequest(length);
      else throw new InvalidMetricException("Len: " + length);
    } else {
      throw new InvalidOperationException("(AD_REQUEST) Video " + tokens[1] + " Does Not Exist");
    }
  }

  /**
   * utility function to validate format of the line
   *
   * @param tokens array of tokens in line
   * @param count line number counter. gets passed on to exception(for debugging)
   * @throws InvalidFormatException user defined exception thrown if invalid line is found
   */
  void validateLineFormat(String[] tokens, int count) throws InvalidFormatException {
    if (tokens[0].equals("ADD_VIDEO") && tokens.length != 2) {
      throw new InvalidFormatException("Line Number: " + count);
    }

    if (tokens[0].equals("REMOVE_VIDEO") && tokens.length != 2) {
      throw new InvalidFormatException("Line Number: " + count);
    }

    if (tokens[0].equals("METRICS")) {
      if ((!tokens[2].equals("VIEWS")
              || !tokens[4].equals("LIKES")
              || !tokens[6].equals("DISLIKES"))
          || tokens.length != 8) {
        throw new InvalidFormatException("Line Number: " + count);
      }
    }

    if (tokens[0].equals("AD_REQUEST")) {
      if ((!tokens[2].equals("LEN")) || tokens.length != 4) {
        throw new InvalidFormatException("Line Number: " + count);
      }
    }
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "LineParser: " + "channel=" + channel + ", fp=" + fp;
  }
}
