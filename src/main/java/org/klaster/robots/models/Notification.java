package org.klaster.robots.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribable_id", referencedColumnName = "id")
    private Subscribable subscribable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notifiable_id", referencedColumnName = "id")
    private Notifiable notifiable;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subscribable getSubscribable() {
        return subscribable;
    }

    public void setSubscribable(Subscribable subscribable) {
        this.subscribable = subscribable;
    }

    public Notifiable getNotifiable() {
        return notifiable;
    }

    public void setNotifiable(Notifiable notifiable) {
        this.notifiable = notifiable;
    }

    public String getSubscribableName() {
        String subscribableSimpleName = hasSubscribable() ? getSubscribable().getName() : "";
        return subscribableSimpleName;
    }

    public boolean hasSubscribable() {
        return subscribable != null;
    }

    public String getNotifiableName() {
        String notifiableSimpleName = hasNotifiable() ? getNotifiable().getName() : "";
        return notifiableSimpleName;
    }

    public boolean hasNotifiable() {
        return notifiable != null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    abstract public String getMessage();
}
