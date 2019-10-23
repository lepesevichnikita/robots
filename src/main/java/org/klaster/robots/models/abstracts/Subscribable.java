package org.klaster.robots.models.abstracts;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Subscribable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subscribable")
    private Set<Notification> notifications;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public Subscribable() {
    }

    public boolean containsNotificationOfType(Class<? extends Notification> notificationType) {
        return getNotifications().stream()
                                 .map(Notification::getClass)
                                 .collect(Collectors.toList())
                                 .contains(notificationType);
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        if (isNotificationsNull()) {
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

    private boolean isNotificationsNull() {
        return this.notifications == null;
    }

    public void addNotification(Notification notification) {
        notification.setSubscribable(this);
        createNotificationsHashSetIfNotExists();
        notifications.add(notification);

    }

    private void createNotificationsHashSetIfNotExists() {
        if (isNotificationsNull()) {
            setNotifications(new HashSet<>());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public abstract String getName();

}
