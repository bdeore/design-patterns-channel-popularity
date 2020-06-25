package channelpopularity.helper;

import channelpopularity._exceptions.InvalidMetricException;

/** utility class to store and encapsulate individual video information */
public class Video {
  private final String videoName;
  private int views;
  private int likes;
  private float score;
  private int dislikes;

  /**
   * parameterized constructor
   *
   * @param videoName video is constructed only with name everything else is set to zero
   */
  public Video(String videoName) {
    this.videoName = videoName;
    this.views = 0;
    this.likes = 0;
    this.dislikes = 0;
    this.score = 0;
  }

  /**
   * setter for views
   *
   * @param views number of views
   */
  public void setViews(int views) {
    this.views = views;
  }

  /**
   * setter for likes
   *
   * @param likes number of likes
   */
  public void setLikes(int likes) {
    this.likes = likes;
  }

  /**
   * setter for dislikes
   *
   * @param dislikes number of dislikes
   */
  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }

  /**
   * utility method to update metrics of the video. also does some basic validations
   *
   * @param video object of video containing updates
   */
  public void updateMetrics(Video video) {
    try {
      if (this.views + video.views >= 0) this.views += video.views;
      else throw new InvalidMetricException("Views: " + video.views);

      if (this.likes + video.likes >= 0) this.likes += video.likes;
      else throw new InvalidMetricException("Likes: " + video.likes);

      if (this.dislikes + video.dislikes >= 0) this.dislikes += video.dislikes;
      else throw new InvalidMetricException("Dislikes: " + video.dislikes);

      calculateScore();
    } catch (InvalidMetricException e) {
      System.out.println(e);
      System.out.println("(processFile method) Terminating Program");
      System.exit(1);
    }
  }

  /**
   * getter for video name
   *
   * @return String name of the video
   */
  public String getVideoName() {
    return videoName;
  }

  /**
   * getter for score
   *
   * @return float score of the individual video
   */
  public float getScore() {
    return score;
  }

  /** method to calculate score of individual video sets to zero if the score is negative */
  private void calculateScore() {
    float score = (views + 2 * (likes - dislikes));
    if (score > 0) {
      this.score = score;
    } else this.score = 0;
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "Video{"
        + "videoName='"
        + videoName
        + '\''
        + ", views="
        + views
        + ", likes="
        + likes
        + ", score="
        + score
        + ", dislikes="
        + dislikes
        + '}';
  }
}
