package ua.service.SeStans.mechanic;

import jakarta.persistence.*;
import ua.service.SeStans.user.User;

@Entity
@Table(name="Mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mechanic_id;
    private Integer experience;
    private String specification;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Float mechanic_profit;

    public Mechanic() {
    }

    public Mechanic(Integer mechanic_id, Integer experience, String specification, User user, Float mechanic_profit) {
        this.mechanic_id = mechanic_id;
        this.experience = experience;
        this.specification = specification;
        this.user = user;
        this.mechanic_profit = mechanic_profit;
    }

    public Mechanic(Integer experience, String specification, User user, Float mechanic_profit) {
        this.experience = experience;
        this.specification = specification;
        this.user = user;
        this.mechanic_profit = mechanic_profit;
    }

    public Integer getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(Integer mechanic_id) {
        this.mechanic_id = mechanic_id;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getMechanic_profit() {
        return mechanic_profit;
    }

    public void setMechanic_profit(Float mechanic_profit) {
        this.mechanic_profit = mechanic_profit;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "mechanic_id=" + mechanic_id +
                ", experience=" + experience +
                ", specification='" + specification + '\'' +
                ", user=" + user +
                ", mechanic_profit=" + mechanic_profit +
                '}';
    }
}
