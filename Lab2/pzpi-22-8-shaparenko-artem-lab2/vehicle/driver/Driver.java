package ua.service.SeStans.vehicle.driver;

import jakarta.persistence.*;
import ua.service.SeStans.user.User;

@Entity
@Table(name = "Drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driver_id;
    private String driver_license;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Driver() {
    }

    public Driver(Integer driver_id, String driver_license, User user) {
        this.driver_id = driver_id;
        this.driver_license = driver_license;
        this.user = user;
    }

    public Driver(String driver_license, User user) {
        this.driver_license = driver_license;
        this.user = user;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driver_id=" + driver_id +
                ", driver_license='" + driver_license + '\'' +
                ", user=" + user +
                '}';
    }
}
