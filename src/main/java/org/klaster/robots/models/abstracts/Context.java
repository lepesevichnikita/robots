package org.klaster.robots.models.abstracts;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */

@Entity
public abstract class Context extends Subscribable {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "context")
    private Set<State> states;

    public Context() {
        setDefaultStateAsCurrentIfNewObject();
    }

    private void setDefaultStateAsCurrentIfNewObject() {
        if (getCreatedAt() == null) {
            State currentState = getDefaultState();
            addCurrentState(currentState);
        }
    }

    private void addCurrentState(State currentState) {
        currentState.setContext(this);
        currentState.setCurrent(true);
        addState(currentState);
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        if (!hasStates()) {
            this.states = states;
        }
        else {
            resetStates(states);
        }
    }

    private void resetStates(Set<State> states) {
        this.states.retainAll(states);
        this.states.addAll(states);
    }

    public State getCurrentState() {
        return getStates().stream().filter(State::isCurrent).findFirst().orElse(null);
    }

    public boolean hasStates() {
        return states != null;
    }

    public void changeCurrentState(State newState) {
        if (!isCurrentState(newState.getClass())) {
            changeStateThroughCurrentState(newState);
        }
    }

    private void changeStateThroughCurrentState(State newState) {
        if (hasCurrentState()) {
            getCurrentState().changeCurrentState(newState);
        }
        else {
            newState.setContext(this);
            newState.changeCurrentState(newState);
        }
    }

    public boolean isCurrentState(Class<? extends State> stateClass) {
        return hasCurrentState() && getCurrentState().getClass() == stateClass;
    }

    public boolean hasCurrentState() {
        return getCurrentState() != null;
    }

    public abstract State getDefaultState();

    public void addState(State newState) {
        createNewStatesSetIfNotExists();
        states.add(newState);
    }

    private void createNewStatesSetIfNotExists() {
        if (!hasStates()) {
            states = new HashSet<>();
        }
    }
}
