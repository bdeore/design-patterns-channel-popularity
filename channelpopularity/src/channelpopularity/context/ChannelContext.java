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

/**
 * context class for state pattern. manages state transitions and holds reference to current states.
 * videos are stores in a Map collection for constant time lookup. keys are video names and values
 * are video objects.
 */
public class ChannelContext implements ContextI {

  private final Map<StateName, StateI> availableStates;
  private final Map<String, Video> videos;
  private final Results results;
  private StateI currentState;
  private float popularityScore;

  /**
   * parameterized constructor for the class. accepts states enum and creates states in
   * availableStates Map
   *
   * @param stateNames enum of possible states
   */
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

  /**
   * method to add a new video to the channel
   *
   * @param newVideo video to be added
   * @throws InvalidOperationException user defined exception thrown on invalid operation
   */
  @Override
  public void addVideo(Video newVideo) throws InvalidOperationException {
    currentState.addVideo(newVideo);
  }

  /**
   * method to remove video from the channel
   *
   * @param video video to be removed
   * @throws InvalidOperationException user defined exception thrown on invalid operation
   */
  @Override
  public void removeVideo(Video video) throws InvalidOperationException {
    currentState.removeVideo(video);
  }

  /**
   * method to add metrics to add metrics of available videos
   *
   * @param videoMetrics video object containing metrics to be updated
   */
  @Override
  public void addMetrics(Video videoMetrics) {
    currentState.addMetrics(videoMetrics);
  }

  /**
   * method to process advertisement requests
   *
   * @param length duration of the advertisement
   */
  @Override
  public void processAdRequest(int length) {
    currentState.processAdRequest(length);
  }

  /**
   * getter for Map of available states
   *
   * @return Map object containing available states
   */
  public Map<StateName, StateI> getAvailableStates() {
    return availableStates;
  }

  /**
   * getter for Results object
   *
   * @return Result object with output stored in the buffer
   */
  public Results getResults() {
    return results;
  }

  /**
   * getter for current state
   *
   * @return State object
   */
  public StateI getCurrentState() {
    return currentState;
  }

  /**
   * setter for current state
   *
   * @param nextState state to transition to
   */
  public void setCurrentState(StateName nextState) {
    if (availableStates.containsKey(nextState)) {
      currentState = availableStates.get(nextState);
    }
  }

  /**
   * getter for popularity score
   *
   * @return float popularity score
   */
  public float getPopularityScore() {
    return popularityScore;
  }

  /**
   * setter for popularity score
   *
   * @param popularityScore new popularity score
   */
  public void setPopularityScore(float popularityScore) {
    this.popularityScore = popularityScore;
  }

  /**
   * getter for Map containing video objects
   *
   * @return Map containing all the videos in the channel
   */
  public Map<String, Video> getVideos() {
    return videos;
  }

  /**
   * method to print output on console
   *
   * @throws EmptyInputFileException user defined exception thrown if input file is empty
   */
  @Override
  public void write() throws EmptyInputFileException {
    results.write();
  }

  /**
   * method to print output to a file
   *
   * @throws EmptyInputFileException user defined exception thrown if input file is empty'
   * @throws IOException io exception
   */
  @Override
  public void write(String fileName) throws IOException, EmptyInputFileException {
    results.write(fileName);
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "ChannelContext: "
        + "availableStates="
        + availableStates
        + ", videos="
        + videos
        + ", results="
        + results
        + ", currentState="
        + currentState
        + ", popularityScore="
        + popularityScore;
  }
}
