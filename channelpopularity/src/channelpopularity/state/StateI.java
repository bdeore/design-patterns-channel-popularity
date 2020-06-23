package channelpopularity.state;

import channelpopularity.helper.Video;

public interface StateI {

  void removeVideo(Video video);

  void addMetrics(Video videoMetrics);

  void processAdRequest(int adLength);
}
