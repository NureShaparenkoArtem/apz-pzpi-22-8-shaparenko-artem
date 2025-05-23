/*
package ua.service.SeStans;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.service.SeStans.station.StationService;

@Component
public class MqttSubscriber {

    private final StationService stationService;

    public MqttSubscriber(StationService stationService) {
        this.stationService = stationService;
    }

    @PostConstruct
    public void init() {
        connectAndSubscribe();
    }

    @Value("${mqtt.broker}")
    private String brokerUrl;

    @Value("${mqtt.topic}")
    private String topic;

    private void connectAndSubscribe() {
        try {
            MqttClient client = new MqttClient(brokerUrl, MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost! " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String payload = new String(message.getPayload());
                    System.out.println("Received message: " + payload);
                    processMessage(payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });

            client.connect(options);
            client.subscribe(topic);
            System.out.println("Subscribed to topic: " + topic);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String payload) {
        // Парсим JSON (можно использовать Jackson или Gson)
        try {
            ObjectMapper mapper = new ObjectMapper();
            StationStatusDto statusDto = mapper.readValue(payload, StationStatusDto.class);
            stationService.updateStationStatus(statusDto);
        } catch (Exception e) {
            System.out.println("Failed to process message: " + e.getMessage());
        }
    }
}*/
