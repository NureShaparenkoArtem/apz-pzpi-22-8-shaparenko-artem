package ua.service.SeStans.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.service.SeStans.station.Station;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public List<Reservation> findByReservationTime(LocalDateTime dateTime);
    public List<Reservation> findByStationAndReservationTime(Station station, LocalDateTime dateTime);
    public List<Reservation> findByMechanicNeed(Boolean bool);
    @Query("SELECT r FROM Reservation r WHERE r.station.station_id = :stationId AND r.reservationTime BETWEEN :start AND :end")
    List<Reservation> findByStationAndDateRange(
            @Param("stationId") Integer stationId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

}
