package ua.service.SeStans.mechanic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.service.SeStans.user.User;
import ua.service.SeStans.user.UserRepository;

import java.util.List;

@Service
public class MechanicService {
    private final MechanicRepository mechanicRepository;
    private final UserRepository userRepository;

    @Autowired
    public MechanicService(MechanicRepository mechanicRepository, UserRepository userRepository) {
        this.mechanicRepository = mechanicRepository;
        this.userRepository = userRepository;
    }

    public List<Mechanic> getMechanics() {
        return mechanicRepository.findAll();
    }

    public Mechanic createMechanic(Integer experience, String specification, Integer userId, Float mechanicProfit) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + userId));

        Mechanic mechanic = new Mechanic(experience, specification, user, mechanicProfit);
        return mechanicRepository.save(mechanic);
    }

    public Mechanic updateMechanic(Integer mechanicId, Integer experience, String specification, Float mechanicProfit){
        Mechanic existingMechanic = mechanicRepository.findById(mechanicId).orElseThrow(() ->
                new IllegalArgumentException("Mechanic not found with ID: " + mechanicId));

        existingMechanic.setExperience(experience);
        existingMechanic.setSpecification(specification);
        existingMechanic.setMechanic_profit(mechanicProfit);

        return mechanicRepository.save(existingMechanic);
    }

    public void deleteMechanic(Integer mechanicId){
        if (!mechanicRepository.existsById(mechanicId)) {
            throw new IllegalArgumentException("Mechanic not found with ID: " + mechanicId);
        }
        mechanicRepository.deleteById(mechanicId);
    }
}
