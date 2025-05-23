package ua.service.SeStans.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehicles();
    }

    @PostMapping
    public Vehicle createVehicle(@RequestParam String license_plate,
                                 @RequestParam String vehicle_type,
                                 @RequestParam String make,
                                 @RequestParam String model,
                                 @RequestParam Integer driver_id){
        return vehicleService.createVehicle(license_plate, vehicle_type, make, model, driver_id);
    }

    @PutMapping(path = "/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable Integer vehicleId,
                                 @RequestParam String license_plate,
                                 @RequestParam String vehicle_type,
                                 @RequestParam String make,
                                 @RequestParam String model){
        return vehicleService.updateVehicle(vehicleId, license_plate, vehicle_type, make, model);
    }

    @DeleteMapping(path = "/{vehicleId}")
    public void deleteVehicle(@PathVariable Integer vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }
}
