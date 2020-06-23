package channelpopularity.state;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

public class HighlyPopularState extends AbstractState {

  public HighlyPopularState(ContextI channelContext) {
    super((ChannelContext) channelContext);
  }

  @Override
  public void removeVideo(Video video) {
    int index = findVideo(video);
    if (index != -1) {
      channel.getVideos().remove(index);
      channel.setPopularityScore(calculateScore());
    } else {
      System.out.println("Video doesn't exist");
    }
  }

  @Override
  public void addMetrics(Video videoMetrics) {
    int index = findVideo(videoMetrics);
    Video temp = channel.getVideos().get(index);
    temp.updateMetrics(videoMetrics);

    channel.setPopularityScore(calculateScore());
    findNextState(channel.getPopularityScore());

    System.out.println("Score: " + temp.getScore());
    System.out.println("Popularity Score: " + channel.getPopularityScore());
  }

  @Override
  public void processAdRequest(int adLength) {
    if (adLength > 1 && adLength <= 30) {
      System.out.println("Approved");
    } else {
      System.out.println("Rejected");
    }
  }

  @Override
  public String toString() {
    return "Highly Popular State";
  }
}
