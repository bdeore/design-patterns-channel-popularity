package channelpopularity.state;

import channelpopularity.context.ContextI;

public class HighlyPopularState extends AbstractState {

  ContextI channelContext;

  public HighlyPopularState(ContextI channelContext) {
    this.channelContext = channelContext;
  }

  @Override
  public void addVideo(String videoName) {}

  @Override
  public void removeVideo(String videoName) {}

  @Override
  public void addMetrics(String videoName) {}

  @Override
  public void processAdRequest() {}

  @Override
  public String toString() {
    return "Highly Popular State";
  }
}
