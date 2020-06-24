package channelpopularity.context;

import channelpopularity._exceptions.EmptyInputFileException;
import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.helper.Video;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI {

  private final Map<StateName, StateI> availableStates;
  private final Map<String, Video> videos;
  private final Results results;
  private StateI currentState;
  private float popularityScore;

  public ChannelContext(List<StateName> stateNames) {
    SimpleStateFactoryI stateFactory = new SimpleStateFactory(this);
    availableStates = new HashMap<>();

    for (StateName state : stateNames) {
      availableStates.put(state, stateFactory.create(state));
    }

    videos = new HashMap<>();
    results = new Results();
    this.currentState = availableStates.get(StateName.UNPOPULAR);
    this.popularityScore = 0;
  }

  @Override
  public void addVideo(Video newVideo) throws InvalidOperationException {
    currentState.addVideo(newVideo);
  }

  @Override
  public void removeVideo(Video video) throws InvalidOperationException {
    currentState.removeVideo(video);
  }

  @Override
  public void addMetrics(Video videoMetrics) {
    currentState.addMetrics(videoMetrics);
  }

  @Override
  public void processAdRequest(int length) {
    currentState.processAdRequest(length);
  }

  public Map<StateName, StateI> getAvailableStates() {
    return availableStates;
  }

  public Results getResults() {
    return results;
  }

  public StateI getCurrentState() {
    return currentState;
  }

  public void setCurrentState(StateName nextState) {
    if (availableStates.containsKey(nextState)) {
      currentState = availableStates.get(nextState);
    }
  }

  public float getPopularityScore() {
    return popularityScore;
  }

  public void setPopularityScore(float popularityScore) {
    this.popularityScore = popularityScore;
  }

  public Map<String, Video> getVideos() {
    return videos;
  }

  @Override
  public void write() throws EmptyInputFileException {
    results.write();
  }

  @Override
  public void write(String fileName) throws IOException, EmptyInputFileException {
    results.write(fileName);
  }
}
