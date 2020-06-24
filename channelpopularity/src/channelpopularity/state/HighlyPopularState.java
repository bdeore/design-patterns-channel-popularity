package channelpopularity.state;

import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

public class HighlyPopularState extends AbstractState {

  private final StateName name = StateName.HIGHLY_POPULAR;

  public HighlyPopularState(ContextI channelContext) {
    super((ChannelContext) channelContext);
  }

  @Override
  public void removeVideo(Video video) throws InvalidOperationException {
    if (findVideo(video)) {
      channel.getVideos().remove(video.getVideoName());
      channel.getResults().store(name + "__VIDEO_REMOVED::" + video.getVideoName());
      channel.setPopularityScore(calculateScore());
      channel.setCurrentState(findNextState(channel.getPopularityScore()));
    } else {
      throw new InvalidOperationException(
          "(REMOVE_VIDEO) " + video.getVideoName() + " Does Not Exist");
    }
  }

  @Override
  public void addMetrics(Video videoMetrics) {
    Video temp = channel.getVideos().get(videoMetrics.getVideoName());
    temp.updateMetrics(videoMetrics);
    channel.setPopularityScore(calculateScore());
    channel
        .getResults()
        .store(name + "__POPULARITY_SCORE_UPDATE::" + format.format(channel.getPopularityScore()));
    channel.setCurrentState(findNextState(channel.getPopularityScore()));
  }

  @Override
  public void processAdRequest(int adLength) {
    if (adLength > 1 && adLength <= 30) {
      channel.getResults().store(name + "__AD_REQUEST::APPROVED");
    } else {
      channel.getResults().store(name + "__AD_REQUEST::REJECTED");
    }
  }

  @Override
  public String toString() {
    return "HIGHLY_POPULAR";
  }
}
