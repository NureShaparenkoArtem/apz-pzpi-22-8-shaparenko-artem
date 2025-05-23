package ua.service.SeStans.station;

import jakarta.persistence.*;
import org.w3c.dom.Text;
import ua.service.SeStans.station.owner.Owner;

import java.time.LocalTime;

@Entity
@Table(name = "Stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer station_id;
    private String station_name;
    private String station_type;
    private String location;
    private Boolean is_free;
    @Column(name = "opening_time")
    private LocalTime openingTime;
    @Column(name = "closing_time")
    private LocalTime closingTime;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private Float hourly_price;
    private String available_tools;

    public Station() {
    }

    public Station(Integer station_id, String station_name, String station_type, String location, Boolean is_free, LocalTime openingTime, LocalTime closingTime, Owner owner, Float hourly_price, String available_tools) {
        this.station_id = station_id;
        this.station_name = station_name;
        this.station_type = station_type;
        this.location = location;
        this.is_free = is_free;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.owner = owner;
        this.hourly_price = hourly_price;
        this.available_tools = available_tools;
    }

    public Station(String station_name, String station_type, String location, Boolean is_free, LocalTime openingTime, LocalTime closingTime, Owner owner, Float hourly_price, String available_tools) {
        this.station_name = station_name;
        this.station_type = station_type;
        this.location = location;
        this.is_free = is_free;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.owner = owner;
        this.hourly_price = hourly_price;
        this.available_tools = available_tools;
    }

    public Integer getStation_id() {
        return station_id;
    }

    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_type() {
        return station_type;
    }

    public void setStation_type(String station_type) {
        this.station_type = station_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIs_free() {
        return is_free;
    }

    public void setIs_free(Boolean is_free) {
        this.is_free = is_free;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Float getHourly_price() {
        return hourly_price;
    }

    public void setHourly_price(Float hourly_price) {
        this.hourly_price = hourly_price;
    }

    public String getAvailable_tools() {
        return available_tools;
    }

    public void setAvailable_tools(String available_tools) {
        this.available_tools = available_tools;
    }

    @Override
    public String toString() {
        return "Station{" +
                "station_id=" + station_id +
                ", station_name='" + station_name + '\'' +
                ", station_type='" + station_type + '\'' +
                ", location='" + location + '\'' +
                ", is_free=" + is_free +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", owner=" + owner +
                ", hourly_price=" + hourly_price +
                ", available_tools='" + available_tools + '\'' +
                '}';
    }
}
