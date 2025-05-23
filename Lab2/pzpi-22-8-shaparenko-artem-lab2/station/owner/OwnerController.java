package ua.service.SeStans.station.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/owner")
public class OwnerController {
    public final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getOwners() {
        return ownerService.getOwners();
    }

    @PostMapping
    public Owner createOwner(@RequestParam String company_name, @RequestParam Integer user_id) {
        return ownerService.createOwner(company_name, user_id);
    }

    @PutMapping(path = "/{ownerId}")
    public Owner updateOwner(@PathVariable Integer ownerId, @RequestParam String company_name) {
        return ownerService.updateOwner(ownerId, company_name);
    }

    @DeleteMapping(path = "/{ownerId}")
    public void deleteOwner(@PathVariable Integer ownerId) {
        ownerService.deleteOwner(ownerId);
    }
}
