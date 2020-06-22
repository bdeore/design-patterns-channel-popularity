package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

public class HighlyPopularState extends AbstractState {

  ContextI channelContext;

  public HighlyPopularState(ContextI channelContext) {
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
  public String toString() {
    return "Highly Popular State";
  }
}
