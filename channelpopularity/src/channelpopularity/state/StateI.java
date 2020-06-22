package channelpopularity.state;

public interface StateI {
  void addVideo(String videoName);

  void removeVideo(String videoName);

  void addMetrics(String videoName);

  void processAdRequest();
}
