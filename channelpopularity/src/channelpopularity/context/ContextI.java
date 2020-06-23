package channelpopularity.context;

import channelpopularity.helper.Video;
import channelpopularity.state.StateName;

public interface ContextI {

  void setCurrentState(StateName state);

  void addVideo(Video newVideo);

  void removeVideo(Video video);

  void addMetrics(Video videoMetrics);

  void processAdRequest(int length);
}
