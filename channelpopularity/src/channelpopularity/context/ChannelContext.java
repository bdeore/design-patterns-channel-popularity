package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI {

  private final Map<StateName, StateI> availableStates;
  private StateI currentState;

  public ChannelContext(List<StateName> stateNames) {
    SimpleStateFactoryI stateFactory = new SimpleStateFactory(this);
    availableStates = new HashMap<>();

    for (StateName state : stateNames) {
      availableStates.put(state, stateFactory.create(state));
    }
    this.currentState = availableStates.get(StateName.UNPOPULAR);
  }

  // Called by the States based on their logic of what the machine state should change to.
  public void setCurrentState(StateName nextState) {
    if (availableStates.containsKey(nextState)) { // for safety.
      currentState = availableStates.get(nextState);
    }
  }

  // Utility method for testing
  public void printAll(List<StateName> stateNames) {
    for (StateName state : stateNames) {
      if (availableStates.containsKey(state)) {
        currentState = availableStates.get(state);
        System.out.println(currentState.toString());
      }
    }
  }
}
