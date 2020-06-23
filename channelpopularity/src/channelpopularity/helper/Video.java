package channelpopularity.helper;

public class Video {
  private final String videoName;
  private int views;
  private int likes;
  private int score;
  private int dislikes;

  public Video(String videoName) {
    this.videoName = videoName;
    this.views = 0;
    this.likes = 0;
    this.dislikes = 0;
    this.score = 0;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }

  public void updateMetrics(Video video) {
    this.views += video.views;
    this.likes += video.likes;
    this.dislikes += video.dislikes;
    calculateScore();
  }

  public String getVideoName() {
    return videoName;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  private void calculateScore() {
    this.score = (views + 2 * (likes - dislikes));
  }
}
