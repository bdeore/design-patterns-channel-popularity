package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

/** Interface for simple state factory */
public interface SimpleStateFactoryI {
  StateI create(StateName state);
}
