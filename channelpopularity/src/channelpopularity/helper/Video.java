package channelpopularity.helper;

public class Video {
  private final String videoName;
  private int views;
  private int likes;
  private float score;
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

    if (this.views + video.views >= 0) this.views += video.views;
    else System.out.println("InvalidValue Exception");

    if (this.likes + video.likes >= 0) this.likes += video.likes;
    else System.out.println("InvalidValue Exception");

    if (this.dislikes + video.dislikes >= 0) this.dislikes += video.dislikes;
    else System.out.println("InvalidValue Exception");

    calculateScore();
  }

  public String getVideoName() {
    return videoName;
  }

  public float getScore() {
    return score;
  }

  private void calculateScore() {
    float score = (views + 2 * (likes - dislikes));
    if (score > 0) {
      this.score = score;
    } else this.score = 0;
  }
}
