package ua.service.SeStans.mechanic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mechanics")
public class MechanicController {
    private final MechanicService mechanicService;

    @Autowired
    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @GetMapping
    public List<Mechanic> getMechanics() {
        return mechanicService.getMechanics();
    }

    @PostMapping
    public Mechanic createMechanic(@RequestParam Integer experience, @RequestParam String specification,
                                   @RequestParam Integer user_id, @RequestParam Float mechanic_profit) {
        return mechanicService.createMechanic(experience, specification, user_id, mechanic_profit);
    }

    @PutMapping(path = "/{mechanicId}")
    public Mechanic updateMechanic(@PathVariable Integer mechanicId, @RequestParam Integer experience,
                                   @RequestParam String specification, @RequestParam Float mechanic_profit) {
        return mechanicService.updateMechanic(mechanicId, experience, specification, mechanic_profit);
    }

    @DeleteMapping(path = "/{mechanicId}")
    public void deleteMechanic(@PathVariable Integer mechanicId) {
        mechanicService.deleteMechanic(mechanicId);
    }
}
