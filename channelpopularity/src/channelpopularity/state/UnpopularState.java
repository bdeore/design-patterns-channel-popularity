package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

public class UnpopularState extends AbstractState {

  ContextI channelContext;

  public UnpopularState(ContextI channelContext) {
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
    return "Unpopular State";
  }
}
