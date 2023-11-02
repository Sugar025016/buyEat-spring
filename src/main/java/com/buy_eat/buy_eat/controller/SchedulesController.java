package com.buy_eat.buy_eat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.config.CustomUserDetails;
import com.buy_eat.buy_eat.model.request.SchedulesRequest;
import com.buy_eat.buy_eat.service.Impl.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class SchedulesController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(path = "/{shopId}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> getShop(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable() int shopId, @Valid @RequestBody SchedulesRequest schedule) {

        return ResponseEntity.ok().body(scheduleService.putSchedule(customUserDetails.getId(),shopId,  schedule));
    }

}
