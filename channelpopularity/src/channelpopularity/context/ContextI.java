package channelpopularity.context;

import channelpopularity._exceptions.EmptyInputFileException;
import channelpopularity._exceptions.InvalidOperationException;
import channelpopularity.helper.Video;
import channelpopularity.state.StateName;
import java.io.IOException;

public interface ContextI {

  void addVideo(Video newVideo) throws InvalidOperationException;

  void removeVideo(Video video) throws InvalidOperationException;

  void addMetrics(Video videoMetrics);

  void processAdRequest(int length);

  void setCurrentState(StateName state);

  void write() throws EmptyInputFileException;

  void write(String fileName) throws IOException, EmptyInputFileException;
}
