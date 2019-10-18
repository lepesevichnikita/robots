package org.klaster.robots.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notifiable extends Context{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "notifiable")
    private Set<Notification> notifications;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        if (!hasNotification()) {
            this.notifications = notifications;
        }
        else {
            resetNotifications(notifications);
        }
    }

    private void resetNotifications(Set<Notification> notifications) {
        this.notifications.retainAll(notifications);
        this.notifications.addAll(notifications);
    }

    private boolean hasNotification() {
        return this.notifications == null;
    }

    public void addNotification(Notification notification) {
        notification.setNotifiable(this);
        if (hasNotification()) {
            setNotifications(new HashSet<>());
        }
        notifications.add(notification);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public abstract String getName();
}
