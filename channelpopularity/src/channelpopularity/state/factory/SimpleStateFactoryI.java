package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {
  StateI create(StateName state);
}
