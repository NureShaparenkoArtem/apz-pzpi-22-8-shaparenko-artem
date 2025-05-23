package ua.service.SeStans.vehicle.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.service.SeStans.user.User;
import ua.service.SeStans.user.UserRepository;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    public List<Driver> getDrivers(){
        return driverRepository.findAll();
    }

    public Driver createDriver(String driverLicense, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + userId));

        Driver driver = new Driver(driverLicense, user);

        return driverRepository.save(driver);
    }

    public Driver updateDriver(Integer driverId, String driverLicense) {
        Driver existingDriver = driverRepository.findById(driverId).orElseThrow(() ->
                new IllegalArgumentException("Driver not found with ID: " + driverId));

        existingDriver.setDriver_license(driverLicense);

        return driverRepository.save(existingDriver);
    }

    public void deleteDriver(Integer driverId){
        if (!driverRepository.existsById(driverId)){
            throw new IllegalArgumentException("Driver not found with ID: " + driverId);
        }
        driverRepository.deleteById(driverId);
    }
}
