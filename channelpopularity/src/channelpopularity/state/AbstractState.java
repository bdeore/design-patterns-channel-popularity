package channelpopularity.state;

import channelpopularity.context.ChannelContext;
import channelpopularity.helper.Video;
import java.util.List;

public abstract class AbstractState implements StateI {

  ChannelContext channel;

  public AbstractState(ChannelContext channel) {
    this.channel = channel;
  }

  public int findVideo(Video video) {
    int index = 0;
    for (Video v : channel.getVideos()) {
      if (v.getVideoName().equals(video.getVideoName())) {
        return index;
      }
      index++;
    }
    return -1;
  }

  public int calculateScore() {
    int updatedScore = 0;
    List<Video> videos = channel.getVideos();
    if (videos.size() > 0) {
      for (Video vid : videos) {
        updatedScore += vid.getScore();
      }
      return (updatedScore / videos.size());
    }
    return 0;
  }

  void findNextState(int score) {
    if (score >= 0 && score <= 1000) {
      channel.setCurrentState(StateName.UNPOPULAR);
    } else if (score > 1000 && score <= 10000) {
      channel.setCurrentState(StateName.MILDLY_POPULAR);
    } else if (score > 10000 && score <= 100000) {
      channel.setCurrentState(StateName.HIGHLY_POPULAR);
    } else if (score > 100000 && score < Integer.MAX_VALUE) {
      channel.setCurrentState(StateName.ULTRA_POPULAR);
    }
  }
}
