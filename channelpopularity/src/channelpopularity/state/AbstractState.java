package channelpopularity.state;

import channelpopularity.helper.Video;

public abstract class AbstractState implements StateI {

  @Override
  public void addVideo(Video newVideo) {}

  @Override
  public void removeVideo(Video video) {}

  @Override
  public void addMetrics(String videoName) {}

  @Override
  public void processAdRequest() {}
}
