package ua.service.SeStans.vehicle.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getVehicles(){
        return driverService.getDrivers();
    }

    @PostMapping
    public Driver createVehicle(@RequestParam String driver_license,
                                 @RequestParam Integer user_id){
        return driverService.createDriver(driver_license, user_id);
    }

    @PutMapping(path = "/{driverId}")
    public Driver updateVehicle(@PathVariable Integer driverId,
                                 @RequestParam String driver_license){
        return driverService.updateDriver(driverId, driver_license);
    }

    @DeleteMapping(path = "/{driverId}")
    public void deleteVehicle(@PathVariable Integer driverId){
        driverService.deleteDriver(driverId);
    }
}
