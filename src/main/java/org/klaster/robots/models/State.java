package org.klaster.robots.models;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/18/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "context_id", referencedColumnName = "id")
    private Context context;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "newState")
    private Set<NotificationAboutStateChange> notificationAboutNewState;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "oldState")
    private Set<NotificationAboutStateChange> notificationAboutOldState;

    public State(Context context) {
        setContext(context);
    }

    public abstract String getName();

    public void changeState(State newState) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean hasContext() {
        return context != null;
    }
}
