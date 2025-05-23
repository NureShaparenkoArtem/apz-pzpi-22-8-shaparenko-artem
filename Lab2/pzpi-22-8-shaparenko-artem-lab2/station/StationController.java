package ua.service.SeStans.station;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.service.SeStans.StationStatusDto;
import ua.service.SeStans.station.owner.Owner;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stations")
public class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    private List<Station> getStations() {
        return stationService.getStations();
    }

    @PostMapping
    public Station createStation(@RequestParam String station_name,
                                 @RequestParam String station_type,
                                 @RequestParam String location,
                                 @Parameter(schema = @Schema(type = "string", format = "time"))
                                     @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime opening_time,
                                 @Parameter(schema = @Schema(type = "string", format = "time"))
                                     @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime closing_time,
                                 @RequestParam Integer owner_id,
                                 @RequestParam Float hourly_price,
                                 @RequestParam String available_tools) {
        return stationService.createStation(station_name, station_type, location, opening_time, closing_time, owner_id, hourly_price, available_tools);
    }

    @PutMapping(path = "/{stationId}")
    public Station updateStation(@PathVariable Integer stationId,
                                 @RequestParam String station_name,
                                 @RequestParam String station_type,
                                 @RequestParam String location,
                                 @Parameter(schema = @Schema(type = "string", format = "time"))
                                    @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime opening_time,
                                 @Parameter(schema = @Schema(type = "string", format = "time"))
                                    @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime closing_time,
                                 @RequestParam Integer owner_id,
                                 @RequestParam Float hourly_price,
                                 @RequestParam String available_tools) {
        return stationService.updateStation(stationId, station_name, station_type, location, opening_time, closing_time, owner_id, hourly_price, available_tools);
    }

//    @PutMapping
//    public void updateStationStatus(@RequestParam Integer stationId, @RequestParam Boolean isFree) {
//        stationService.updateStationStatus(stationId, isFree);
//    }

    @PutMapping
    public void updateStationStatus(@RequestBody StationStatusDto stationStatusDto) {
        stationService.updateStationStatus(stationStatusDto);
    }

    @DeleteMapping(path = "/{stationId}")
    public void deleteStation(@PathVariable Integer stationId) {
        stationService.deleteStation(stationId);
    }

    @GetMapping(path = "/getMonthEarn/{stationId}")
    public Float getMonthlyEarnings(@PathVariable Integer stationId) {
        return stationService.getMonthlyEarnings(stationId);
    }

    @GetMapping(path = "/getYearEarn/{stationId}")
    public Float getYearlyEarnings(@PathVariable Integer stationId) {
        return stationService.getYearlyEarnings(stationId);
    }

    @GetMapping(path = "/getAvgMonthEarn/{stationId}")
    public Float getAverageMonthlyEarningsForLastYear(@PathVariable Integer stationId) {
        return stationService.getAverageMonthlyEarningsForLastYear(stationId);
    }
}
