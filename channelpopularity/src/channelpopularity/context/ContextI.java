package channelpopularity.context;

import channelpopularity.helper.Video;
import channelpopularity.state.StateName;
import java.util.List;

public interface ContextI {

  void printAll(List<StateName> stateNames);

  void setCurrentState(StateName state);

  void addVideo(Video newVideo);

  void removeVideo(Video video);

  void addMetrics(String videoName);

  void processAdRequest();
}
