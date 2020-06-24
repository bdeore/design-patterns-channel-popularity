package channelpopularity.state;

import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.context.ChannelContext;
import channelpopularity.helper.Video;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public abstract class AbstractState implements StateI {

  ChannelContext channel;
  DecimalFormat format = new DecimalFormat("0.###");

  public AbstractState(ChannelContext channel) {
    this.channel = channel;
  }

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

  public boolean findVideo(Video video) {
    return (channel.getVideos().containsKey(video.getVideoName()));
  }

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
    return updatedScore;
  }

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
}
