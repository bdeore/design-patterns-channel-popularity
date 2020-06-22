package channelpopularity.state;

import channelpopularity.context.ContextI;

public class MildlyPopularState extends AbstractState {

  ContextI channelContext;

  public MildlyPopularState(ContextI channelContext) {
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
    return "Mildly Popular State";
  }
}
