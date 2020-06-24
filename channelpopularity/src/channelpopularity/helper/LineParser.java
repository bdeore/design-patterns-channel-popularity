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

public class LineParser {

  private final ChannelContext channel;
  private final FileProcessor fp;

  public LineParser(FileProcessor fp, ContextI channel) {
    this.fp = fp;
    this.channel = (ChannelContext) channel;
  }

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

  void processNewVideo(String[] tokens) throws InvalidOperationException {
    Video newVideo = new Video(tokens[1]);
    channel.addVideo(newVideo);
  }

  void removeVideo(String[] tokens) throws InvalidOperationException {
    Video video = new Video(tokens[1]);
    channel.removeVideo(video);
  }

  void processMetrics(String[] tokens) throws NumberFormatException, InvalidMetricException {
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
  }

  void processAdRequest(String[] tokens) throws InvalidMetricException, InvalidOperationException {

    if (channel.getVideos().containsKey(tokens[1])) {
      int length = Integer.parseInt(tokens[3]);
      if (length > 0) channel.processAdRequest(length);
      else throw new InvalidMetricException("Len: " + length);
    } else {
      throw new InvalidOperationException("(AD_REQUEST) Video " + tokens[1] + " Does Not Exist");
    }
  }

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
}
