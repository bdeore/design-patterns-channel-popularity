package channelpopularity.context;

import channelpopularity.helper.Video;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI {

  private final Map<StateName, StateI> availableStates;
  private final List<Video> videos;
  private StateI currentState;
  private int popularityScore;

  public ChannelContext(List<StateName> stateNames) {
    SimpleStateFactoryI stateFactory = new SimpleStateFactory(this);
    availableStates = new HashMap<>();

    for (StateName state : stateNames) {
      availableStates.put(state, stateFactory.create(state));
    }

    videos = new ArrayList<>();
    this.currentState = availableStates.get(StateName.UNPOPULAR);
    this.popularityScore = 0;
  }

  @Override
  public void addVideo(Video newVideo) {
    if (findVideo(newVideo) == -1) {
      videos.add(newVideo);
    }
  }

  @Override
  public void removeVideo(Video video) {
    int index = findVideo(video);
    if (index != -1) {
      videos.remove(index);
    } else {
      System.out.println("Video doesn't exist");
    }
  }

  @Override
  public void addMetrics(String videoName) {}

  @Override
  public void processAdRequest() {}

  // Called by the States based on their logic of what the machine state should change to.
  public void setCurrentState(StateName nextState) {
    if (availableStates.containsKey(nextState)) { // for safety.
      currentState = availableStates.get(nextState);
    }
  }

  public int findVideo(Video video) {
    int index = 0;
    for (Video v : videos) {
      if (v.getVideoName().equals(video.getVideoName())) {
        return index;
      }
      index++;
    }
    return -1;
  }

  // Utility method for testing
  public void printAll(List<StateName> stateNames) {
    for (Video vid : videos) {
      System.out.println(vid.getVideoName());
    }

    for (StateName state : stateNames) {
      if (availableStates.containsKey(state)) {
        currentState = availableStates.get(state);
        System.out.println(currentState.toString());
      }
    }
  }
}
