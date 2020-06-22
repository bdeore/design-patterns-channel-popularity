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

  public void updateMetrics(int views, int likes, int dislikes) {
    this.views += views;
    this.likes += likes;
    this.dislikes += dislikes;
    calculateScore();
  }

  public String getVideoName() {
    return videoName;
  }

  public int getScore() {
    return score;
  }

  private void calculateScore() {
    this.score = (views + 2 * (likes - dislikes));
  }
}
