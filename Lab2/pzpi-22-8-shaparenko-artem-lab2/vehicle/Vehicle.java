package ua.service.SeStans.vehicle;

import jakarta.persistence.*;
import ua.service.SeStans.vehicle.driver.Driver;

@Entity
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicle_id;
    @Column(name = "license_plate")
    private String licensePlate;
    private String vehicle_type;
    private String make;
    private String model;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Vehicle() {
    }

    public Vehicle(Integer vehicle_id, String license_plate, String vehicle_type, String make, String model, Driver driver) {
        this.vehicle_id = vehicle_id;
        this.licensePlate = license_plate;
        this.vehicle_type = vehicle_type;
        this.make = make;
        this.model = model;
        this.driver = driver;
    }

    public Vehicle(String license_plate, String vehicle_type, String make, String model, Driver driver) {
        this.licensePlate = license_plate;
        this.vehicle_type = vehicle_type;
        this.make = make;
        this.model = model;
        this.driver = driver;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getLicense_plate() {
        return licensePlate;
    }

    public void setLicense_plate(String license_plate) {
        this.licensePlate = license_plate;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", license_plate='" + licensePlate + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", driver=" + driver +
                '}';
    }
}
