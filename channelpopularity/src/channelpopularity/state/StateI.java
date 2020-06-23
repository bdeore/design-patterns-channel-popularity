package channelpopularity.state;

import channelpopularity.helper.Video;

public interface StateI {

  void addVideo(Video newVideo);

  void removeVideo(Video video);

  void addMetrics(Video videoMetrics);

  void processAdRequest(int adLength);
}
