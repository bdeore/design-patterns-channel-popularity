package channelpopularity.context;

import channelpopularity.state.StateName;
import java.util.List;

public interface ContextI {

  void printAll(List<StateName> stateNames);

  void setCurrentState(StateName state);
}
