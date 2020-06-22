package channelpopularity.helper;

import channelpopularity.context.ContextI;
import channelpopularity.util.FileProcessor;
import java.io.IOException;

public class LineParser {

  private FileProcessor fp;
  private ContextI channel;

  public LineParser(FileProcessor fp, ContextI channel) {
    this.fp = fp;
    this.channel = channel;
  }

  public void processFile() {
    try {
      String line = fp.poll();
      String delimiters = "[_]{2}|[:,=\\[\\] ]+";
      while (line != null) {
        String[] tokens = line.split(delimiters);

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
        }

        line = fp.poll();
      }

    } catch (IOException e) {
      System.out.println(e);
      System.out.println("(processFile method) Terminating Program");
      System.exit(1);
    }
  }

  void processNewVideo(String[] tokens) {
    Video newVideo = new Video(tokens[1]);
    channel.addVideo(newVideo);
  }

  void removeVideo(String[] tokens) {
    Video video = new Video(tokens[1]);
    //  channel.removeVideo(video);
  }

  void processMetrics(String[] tokens) {
    for (String token : tokens) {
      System.out.println(token);
    }
    System.out.println("\n");
  }
}
