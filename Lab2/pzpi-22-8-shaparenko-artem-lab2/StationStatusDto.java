package ua.service.SeStans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class StationStatusDto {
    @JsonProperty("stationId")  // Поле "stationId" из JSON маппится на поле "stationId" в классе
    private Integer stationId;

    @JsonProperty("isFree")  // Поле "isFree" из JSON маппится на поле "free" в классе
    private Boolean free;

    // Геттеры и сеттеры
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }
}
