package com.crm.controller;

import com.crm.entity.Bus;
import com.crm.entity.Post;
import com.crm.repository.BusRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    private BusRepository busRepository;

    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
       @PostMapping
    public String createBus(
            @RequestBody Bus bus) {
        busRepository.save(bus);
        return "saved";
    }
}
