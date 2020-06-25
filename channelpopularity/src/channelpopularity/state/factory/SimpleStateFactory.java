package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.MildlyPopularState;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.UnpopularState;

/**
 * Simple factory method to create new states
 *
 * <p>DESIGN PRINCIPLE: encapsulate what varies
 */
public class SimpleStateFactory implements SimpleStateFactoryI {

  ContextI channelContext;

  public SimpleStateFactory(ContextI channelContext) {
    this.channelContext = channelContext;
  }

  /**
   * create method uses enum of permissible states and returns a requested state
   *
   * <p>DESIGN PRINCIPLE: program to the interface not implementation
   *
   * @param state requested state object
   * @return State object
   */
  @Override
  public StateI create(StateName state) {

    switch (state) {
      case UNPOPULAR:
        return new UnpopularState(channelContext);

      case MILDLY_POPULAR:
        return new MildlyPopularState(channelContext);

      case HIGHLY_POPULAR:
        return new HighlyPopularState(channelContext);

      case ULTRA_POPULAR:
        return new UltraPopularState(channelContext);
    }

    return null;
  }

  /**
   * toString method
   *
   * @return String containing debugging info
   */
  @Override
  public String toString() {
    return "SimpleStateFactory: " + "channelContext=" + channelContext;
  }
}
