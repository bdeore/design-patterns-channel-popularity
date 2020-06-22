package channelpopularity.state;

import channelpopularity.helper.Video;

public interface StateI {
  void addVideo(Video newVideo);

  void removeVideo(Video video);

  void addMetrics(String videoName);

  void processAdRequest();
}
