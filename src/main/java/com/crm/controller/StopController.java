package com.crm.controller;

import com.crm.entity.Bus;
import com.crm.entity.Stop;
import com.crm.repository.StopRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stop")
public class StopController {

    private StopRepository stopRepository;

    public StopController(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }
    @PostMapping
    public String createBus(
            @RequestBody Stop stop) {
        stopRepository.save(stop);
        return "saved";
    }
}
