package ua.service.SeStans.station.owner;

import jakarta.persistence.*;
import ua.service.SeStans.user.User;

@Entity
@Table(name="Station_owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer owner_id;
    private String company_name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Owner() {
    }

    public Owner(Integer owner_id, String company_name, User user) {
        this.owner_id = owner_id;
        this.company_name = company_name;
        this.user = user;
    }

    public Owner(String company_name, User user) {
        this.company_name = company_name;
        this.user = user;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "owner_id=" + owner_id +
                ", company_name='" + company_name + '\'' +
                ", user=" + user +
                '}';
    }
}
