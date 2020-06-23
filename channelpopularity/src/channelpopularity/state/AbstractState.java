package channelpopularity.state;

import channelpopularity.helper.Video;

public abstract class AbstractState implements StateI {

  //  ContextI channelContext;
  //
  //  public AbstractState(ContextI channelContext) {
  //    this.channelContext = channelContext;
  //  }
  //
  //  @Override
  //  public void addVideo(Video newVideo) {
  //    channelContext.addVideo(newVideo);
  //  }

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
}
