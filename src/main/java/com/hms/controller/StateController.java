package com.hms.controller;

import com.hms.entity.State;
import com.hms.repository.StateRepository;
import com.hms.service.PropertyService;
import com.hms.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/state")
public class StateController {

    private StateService stateService;
    private PropertyService propertyService;
    private StateRepository repository;


    public StateController(StateService stateService, PropertyService propertyService, StateRepository repository) {
        this.stateService = stateService;
        this.propertyService = propertyService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<State> addState(@RequestBody State state){

        State addedStates = stateService.addStates(state);

        return new ResponseEntity<>(addedStates, HttpStatus.CREATED);
    }

    // Method to delete state by ID and its related properties
    @DeleteMapping("/{stateId}")
    public ResponseEntity<String> deleteState(@PathVariable Long stateId) {

        if(!repository.existsById(stateId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }

        // Delete all properties related to the state
        propertyService.deletePropertiesByStateId(stateId);

        // Delete the state itself
        stateService.deleteStateById(stateId);

        return new ResponseEntity<>("State and related properties deleted successfully",HttpStatus.OK);
    }
}
