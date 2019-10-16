package org.klaster.robots.models;

import javax.persistence.*;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/14/19
 * @project robots
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.WAITING;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name ="robot_id", referencedColumnName = "id")
    private Robot robot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public enum Status {
        PROCESSING, COMPLETED, WAITING
    }

    public Boolean isGeneral() {
        return robot == null;
    }
    public Boolean isWaiting() { return status == Status.WAITING; }
    public Boolean isCompleted() { return status == Status.COMPLETED; }
    public Boolean isProcessing() { return status == Status.PROCESSING; }
}
