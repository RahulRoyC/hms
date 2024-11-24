package com.hms.service;

import com.hms.entity.State;
import com.hms.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State addStates(State state) {
        State save = stateRepository.save(state);
        return save;
    }

    @Transactional
    public void deleteStateById(Long stateId) {
        stateRepository.deleteById(stateId);
    }
}
