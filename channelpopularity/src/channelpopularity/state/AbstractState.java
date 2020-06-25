package channelpopularity.state;

import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.context.ChannelContext;
import channelpopularity.helper.Video;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

/** Abstract Class that contains implementations common to all the states */
public abstract class AbstractState implements StateI {

  ChannelContext channel;
  // to format output with correct precision
  DecimalFormat format = new DecimalFormat("0.###");

  /**
   * Parameterized constructor
   *
   * @param channel instance of context class
   */
  public AbstractState(ChannelContext channel) {
    this.channel = channel;
  }

  /**
   * method to add new video to the channel. all the states use same method for adding videos.
   * implemented here to avoid duplicated code
   *
   * @param newVideo video to be added
   * @throws InvalidOperationException user defined exception thrown on invalid operation
   */
  @Override
  public void addVideo(Video newVideo) throws InvalidOperationException {
    if (!findVideo(newVideo)) {
      channel.getVideos().put(newVideo.getVideoName(), newVideo);
      channel
          .getResults()
          .store(channel.getCurrentState() + "__VIDEO_ADDED::" + newVideo.getVideoName());
      channel.setPopularityScore(calculateScore());
      channel.setCurrentState(findNextState(channel.getPopularityScore()));
    } else {
      throw new InvalidOperationException(
          "(ADD_VIDEO) " + newVideo.getVideoName() + " Already Exists");
    }
  }

  /**
   * method to check if the video already exists in the channel. returns true if video exists, false
   * otherwise
   *
   * @param video video to find
   * @return Boolean value indicating the presence or absence of video
   */
  public boolean findVideo(Video video) {
    return (channel.getVideos().containsKey(video.getVideoName()));
  }

  /**
   * method to calculate score of the channel. return zero if the
   *
   * @return score if greater than zero, zero otherwise
   */
  public float calculateScore() {
    float updatedScore = 0;
    Map<String, Video> videos = channel.getVideos();
    Set<String> keys = videos.keySet();
    if (videos.size() > 0) {
      for (String key : keys) {
        updatedScore += videos.get(key).getScore();
      }
      updatedScore /= keys.size();
    }
    return (updatedScore >= 0) ? updatedScore : 0;
  }

  /**
   * utility function used by all states to determine next state to transition to. gives us a single
   * place to perform updates in case the logic changes.
   *
   * <p>DESIGN PRINCIPLE: encapsulate what varies
   *
   * @param score current popularity score
   * @return Enum of state to transition to
   */
  public StateName findNextState(float score) {
    if (score >= 0 && score <= 1000) {
      return StateName.UNPOPULAR;
    } else if (score > 1000 && score <= 10000) {
      return StateName.MILDLY_POPULAR;
    } else if (score > 10000 && score <= 100000) {
      return StateName.HIGHLY_POPULAR;
    } else if (score > 100000 && score <= Integer.MAX_VALUE) {
      return StateName.ULTRA_POPULAR;
    }
    System.out.println("throw UnknownState Exception");
    return null;
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "AbstractState: " + "channel = " + channel + ", format = " + format;
  }
}
