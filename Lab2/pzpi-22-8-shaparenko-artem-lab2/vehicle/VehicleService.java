package ua.service.SeStans.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.service.SeStans.vehicle.driver.Driver;
import ua.service.SeStans.vehicle.driver.DriverRepository;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle createVehicle(String licensePlate, String vehicleType, String make, String model, Integer driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() ->
                new IllegalArgumentException("Driver not found with ID: " + driverId));

        if (vehicleRepository.existsByLicensePlate(licensePlate)) {
            throw new IllegalArgumentException("Vehicle with this license plate already exists");
        }

        Vehicle vehicle = new Vehicle(licensePlate, vehicleType, make, model, driver);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Integer vehicleId, String licensePlate, String vehicleType, String make, String model) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId).orElseThrow(() ->
                new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));

        existingVehicle.setLicense_plate(licensePlate);
        existingVehicle.setVehicle_type(vehicleType);
        existingVehicle.setMake(make);
        existingVehicle.setModel(model);

        return vehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(Integer vehicleId){
        if (!vehicleRepository.existsById(vehicleId)){
            throw new IllegalArgumentException("Vehicle not found with ID: " + vehicleId);
        }
        vehicleRepository.deleteById(vehicleId);
    }
}
