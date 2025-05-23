package ua.service.SeStans.station;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.service.SeStans.StationStatusDto;
import ua.service.SeStans.mechanic.Mechanic;
import ua.service.SeStans.reservation.Reservation;
import ua.service.SeStans.reservation.ReservationRepository;
import ua.service.SeStans.reservation.ReservationService;
import ua.service.SeStans.station.owner.Owner;
import ua.service.SeStans.station.owner.OwnerRepository;
import ua.service.SeStans.user.User;
import ua.service.SeStans.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@EnableScheduling
public class StationService {
    private final StationRepository stationRepository;
    private final OwnerRepository ownerRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public StationService(StationRepository stationRepository, OwnerRepository ownerRepository, ReservationRepository reservationRepository) {
        this.stationRepository = stationRepository;
        this.ownerRepository = ownerRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }

    public Station createStation(String stationName,
                                 String stationType,
                                 String location,
                                 LocalTime openingTime,
                                 LocalTime closingTime,
                                 Integer ownerId,
                                 Float hourly_price,
                                 String availableTools) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() ->
                new IllegalArgumentException("Owner not found with ID: " + ownerId));

        Station station = new Station(stationName, stationType, location, true, openingTime, closingTime, owner, hourly_price, availableTools);

        return stationRepository.save(station);
    }

    public Station updateStation(Integer stationId,
                                 String stationName,
                                 String stationType,
                                 String location,
                                 LocalTime openingTime,
                                 LocalTime closingTime,
                                 Integer ownerId,
                                 Float hourly_price,
                                 String availableTools){
        Station existingStation = stationRepository.findById(stationId).orElseThrow(() ->
                new IllegalArgumentException("Station not found with ID: " + stationId));

        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() ->
                new IllegalArgumentException("Owner not found with ID: " + ownerId));

        existingStation.setStation_name(stationName);
        existingStation.setStation_type(stationType);
        existingStation.setLocation(location);
        existingStation.setOpeningTime(openingTime);
        existingStation.setClosingTime(closingTime);
        existingStation.setOwner(owner);
        existingStation.setHourly_price(hourly_price);
        existingStation.setAvailable_tools(availableTools);

        return stationRepository.save(existingStation);
    }

    public void deleteStation(Integer stationId){
        if (!stationRepository.existsById(stationId)) {
            throw new IllegalArgumentException("Station not found with ID: " + stationId);
        }

        stationRepository.deleteById(stationId);
    }

    @Scheduled(cron = "0 0/15 7-12 * * ?")
    public void openStations() {
        LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
        int updatedStations = 0;
        List<Station> stations = stationRepository.findByOpeningTime(currentTime);

        for (Station station: stations) {
            List<Reservation> reservations = reservationRepository.findByStationAndReservationTime(station,
                    LocalDateTime.now().withSecond(0).withNano(0));
            Boolean isStationOccupied = !reservations.isEmpty();
            if (!isStationOccupied) {
                station.setIs_free(true);
                stationRepository.save(station);
                updatedStations++;
            }
        }

        System.out.println("[" + currentTime + "] Stations opened: " + updatedStations);
    }

    @Scheduled(cron = "0 0/15 15-23 * * ?")
    public void closeStations() {
        LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
        int updatedStations = 0;
        List<Station> stations = stationRepository.findByClosingTime(currentTime);

        for (Station station: stations) {
            station.setIs_free(false);
            stationRepository.save(station);
            updatedStations++;
        }
        System.out.println("[" + currentTime + "] Stations closed: " + updatedStations);
    }

//    public void updateStationStatus(Integer stationId, Boolean isFree) {
//        Station station = stationRepository.findById(stationId).orElseThrow(() ->
//                new IllegalArgumentException("Station not found with ID: " + stationId));
//
//        station.setIs_free(isFree);
//
//        stationRepository.save(station);
//    }

    public void updateStationStatus(StationStatusDto stationStatusDto) {
        Integer stationId = stationStatusDto.getStationId();
        Station station = stationRepository.findById(stationId).orElseThrow(() ->
                new IllegalArgumentException("Station not found with ID: " + stationId));

        station.setIs_free(stationStatusDto.getFree());

        stationRepository.save(station);
    }

    public Float getStationEarningsForPeriod(Integer stationId, LocalDateTime from, LocalDateTime to) {
        List<Reservation> reservations = reservationRepository.findByStationAndDateRange(stationId, from, to);
        return reservations.stream()
                .map(Reservation::getPrice)
                .reduce(0f, Float::sum);
    }

    public Float getMonthlyEarnings(Integer stationId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return getStationEarningsForPeriod(stationId, startOfMonth, now);
    }

    public Float getYearlyEarnings(Integer stationId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfYear = now.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return getStationEarningsForPeriod(stationId, startOfYear, now);
    }

    public Float getAverageMonthlyEarningsForLastYear(Integer stationId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneYearAgo = now.minusYears(1);
        Float total = getStationEarningsForPeriod(stationId, oneYearAgo, now);
        return total / 12f;
    }

}
