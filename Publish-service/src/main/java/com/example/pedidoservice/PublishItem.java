package com.example.pedidoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class PublishItem {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /*
        {
            "id": 1,
            "productTitle": "",
            "brandName": "",
            "productDescription": "",
            "productQuantity": 1,
            "rechargeLimit": 0,
            "providerId": 1,
            "warehouseId": 1
        }
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearItem(@RequestBody Map<String, Object> item){

        if((int)item.get("id") == 0){
            return ResponseEntity.badRequest().body(Map.of("ERROR", "Id cannot be 0"));
        }
        if((int)item.get("productQuantity") < 0){
            return ResponseEntity.badRequest().body(Map.of("ERROR", "Quantity cannot be less than 0"));
        }
        if((int)item.get("rechargeLimit") < 0){
            return ResponseEntity.badRequest().body(Map.of("ERROR", "Limit cannot be less than 0"));
        }

        kafkaTemplate.send("item-topic", item);
        return ResponseEntity.ok(item);
    }
}
