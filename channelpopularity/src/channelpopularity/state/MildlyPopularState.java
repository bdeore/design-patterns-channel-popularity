package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

public class MildlyPopularState extends AbstractState {

  ContextI channelContext;

  public MildlyPopularState(ContextI channelContext) {
    this.channelContext = channelContext;
  }

  @Override
  public void addVideo(Video newVideo) {}

  @Override
  public void removeVideo(Video video) {}

  @Override
  public void addMetrics(String videoName) {}

  @Override
  public void processAdRequest() {}

  @Override
  public int calculateScore() {

    return 0;
  }

  @Override
  public String toString() {
    return "Mildly Popular State";
  }
}
