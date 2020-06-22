package channelpopularity.state;

public abstract class AbstractState implements StateI {

  @Override
  public void addVideo(String videoName) {}

  @Override
  public void removeVideo(String videoName) {}

  @Override
  public void addMetrics(String videoName) {}

  @Override
  public void processAdRequest() {}
}
