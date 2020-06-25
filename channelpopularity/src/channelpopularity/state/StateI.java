package channelpopularity.state;

import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.helper.Video;

/** Common Interface for States all states need to implement methods specified here */
public interface StateI {

  void addVideo(Video newVideo) throws InvalidOperationException;

  void removeVideo(Video video) throws InvalidOperationException;

  void addMetrics(Video videoMetrics);

  void processAdRequest(int adLength);
}
