package channelpopularity.context;

import channelpopularity.helper.Video;
import channelpopularity.state.StateName;
import java.io.IOException;

public interface ContextI {

  void addVideo(Video newVideo);

  void removeVideo(Video video);

  void addMetrics(Video videoMetrics);

  void processAdRequest(int length);

  void setCurrentState(StateName state);

  void write();

  void write(String fileName) throws IOException;
}
