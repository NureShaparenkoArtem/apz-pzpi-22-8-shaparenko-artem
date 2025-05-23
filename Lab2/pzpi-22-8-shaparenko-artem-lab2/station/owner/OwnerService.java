package ua.service.SeStans.station.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.service.SeStans.mechanic.Mechanic;
import ua.service.SeStans.user.User;
import ua.service.SeStans.user.UserRepository;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    public List<Owner> getOwners(){
        return ownerRepository.findAll();
    }

    public Owner createOwner(String companyName, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Owner owner = new Owner(companyName, user);

        return ownerRepository.save(owner);
    }

    public Owner updateOwner(Integer ownerId, String companyName) {
        Owner existingOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + ownerId));

        existingOwner.setCompany_name(companyName);

        return ownerRepository.save(existingOwner);
    }

    public void deleteOwner(Integer ownerId) {
        if (!ownerRepository.existsById(ownerId)) {
            throw new IllegalArgumentException("Owner not found with ID: " + ownerId);
        }
        ownerRepository.deleteById(ownerId);
    }
}
