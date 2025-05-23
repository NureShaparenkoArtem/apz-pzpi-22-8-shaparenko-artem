package ua.service.SeStans.reservation;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.service.SeStans.mechanic.Mechanic;
import ua.service.SeStans.mechanic.MechanicRepository;
import ua.service.SeStans.station.Station;
import ua.service.SeStans.station.StationRepository;
import ua.service.SeStans.user.User;
import ua.service.SeStans.vehicle.Vehicle;
import ua.service.SeStans.vehicle.VehicleRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@EnableScheduling
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final StationRepository stationRepository;
    private final VehicleRepository vehicleRepository;
    private final MechanicRepository mechanicRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              StationRepository stationRepository,
                              VehicleRepository vehicleRepository,
                              MechanicRepository mechanicRepository) {
        this.reservationRepository = reservationRepository;
        this.stationRepository = stationRepository;
        this.vehicleRepository = vehicleRepository;
        this.mechanicRepository = mechanicRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(LocalDateTime reservationTime,
                                         LocalDateTime reservationEndTime,
                                         String status,
                                         Boolean mechanicNeed,
                                         Integer stationId,
                                         Integer vehicleId,
                                         Integer mechanicId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found with ID: " + stationId));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));

        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new IllegalArgumentException("Mechanic not found with ID: " + mechanicId));

        Float reservationPrice = (float)Math.ceil(reservationTime.until(reservationEndTime, ChronoUnit.HOURS)) * station.getHourly_price();

        Reservation reservation = new Reservation(reservationTime, reservationEndTime,
                status, mechanicNeed, reservationPrice, station, vehicle, mechanicNeed ? mechanic : null);

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Integer reservationId,
                                         LocalDateTime reservationTime,
                                         LocalDateTime reservationEndTime,
                                         String status,
                                         Boolean mechanicNeed,
                                         Integer stationId,
                                         Integer vehicleId,
                                         Integer mechanicId) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new IllegalArgumentException("Reservation not found with ID:" + reservationId));

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found with ID: " + stationId));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));

        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new IllegalArgumentException("Mechanic not found with ID: " + mechanicId));

        Float reservationPrice = (float)Math.ceil(reservationTime.until(reservationEndTime, ChronoUnit.HOURS)) * station.getHourly_price();

        existingReservation.setReservationTime(reservationTime);
        existingReservation.setReservation_end_time(reservationEndTime);
        existingReservation.setStatus(status);
        existingReservation.setMechanicNeed(mechanicNeed);
        existingReservation.setPrice(reservationPrice);
        existingReservation.setStation(station);
        existingReservation.setVehicle(vehicle);
        existingReservation.setMechanic(mechanic);

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Integer reservationId) {
        if (!reservationRepository.existsById(reservationId)){
            throw new IllegalArgumentException("Reservation not found with ID:" + reservationId);
        }
        reservationRepository.deleteById(reservationId);
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void occupyStation() {
        LocalDateTime currentTime = LocalDateTime.now().withSecond(0).withNano(0);
        int updatedReservations = 0;
        List<Reservation> activeReservations = reservationRepository.findByReservationTime(currentTime);

        for (Reservation reservation: activeReservations) {
            Station station = reservation.getStation();
            station.setIs_free(false);
            stationRepository.save(station);
            updatedReservations++;
        }
        System.out.println("[" + currentTime + "] Stations occupied: " + updatedReservations);
    }

    public void updateReservationPrice(Integer reservationId, Float mechanicPrice) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new IllegalArgumentException("Reservation not found with ID:" + reservationId));

        existingReservation.setPrice(existingReservation.getPrice() + mechanicPrice);

        reservationRepository.save(existingReservation);
    }
}
