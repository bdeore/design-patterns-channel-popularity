package channelpopularity.state;

import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.helper.Video;

/** State class representing mildly popular state */
public class MildlyPopularState extends AbstractState {

  private final StateName name = StateName.MILDLY_POPULAR;

  public MildlyPopularState(ContextI channelContext) {
    super((ChannelContext) channelContext);
  }

  /**
   * method to remove video from the channel
   *
   * @param video video to be removed
   * @throws InvalidOperationException user defined exception thrown on invalid operation
   */
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

  /**
   * method to add metrics to add metrics of available videos
   *
   * @param videoMetrics video object containing metrics to be updated
   */
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

  /**
   * method to process advertisement requests
   *
   * @param adLength duration of the advertisement
   */
  @Override
  public void processAdRequest(int adLength) {
    if (adLength > 1 && adLength <= 20) {
      channel.getResults().store(name + "__AD_REQUEST::APPROVED");
    } else {
      channel.getResults().store(name + "__AD_REQUEST::REJECTED");
    }
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "MildlyPopularState " + "name=" + name;
  }
}
