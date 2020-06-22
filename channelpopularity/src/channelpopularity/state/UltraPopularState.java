package channelpopularity.state;

import channelpopularity.context.ContextI;

public class UltraPopularState extends AbstractState {

  ContextI channelContext;

  public UltraPopularState(ContextI channelContext) {
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
      return "Ultra Popular State";
  }
}
