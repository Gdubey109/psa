package com.crm.controller;

import com.crm.entity.Bus;
import com.crm.entity.BusStops;
import com.crm.entity.Stop;
import com.crm.repository.BusRepository;
import com.crm.repository.BusStopsRepository;
import com.crm.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/busStop")
public class BusStopController {
   private BusRepository busRepository;
   private StopRepository stopRepository;
   private BusStopsRepository busStopsRepository;

   public BusStopController(BusRepository busRepository, StopRepository stopRepository, BusStopsRepository busStopsRepository){
       this.busRepository = busRepository;
       this.stopRepository = stopRepository;
       this.busStopsRepository = busStopsRepository;
   }
   @PostMapping
    public ResponseEntity<BusStops> allocateBusRoute(
            @RequestParam long busId,
            @RequestParam long stopId,
            @RequestBody BusStops busStops
    ){
        Bus bus = busRepository.findById(busId).get();
        Stop stop = stopRepository.findById(stopId).get();

        busStops.setBus(bus);
        busStops.setStop(stop);
        BusStops savedEntity= busStopsRepository.save(busStops);

        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
}
