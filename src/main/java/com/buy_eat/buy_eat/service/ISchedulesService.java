package com.buy_eat.buy_eat.service;

import com.buy_eat.buy_eat.model.request.SchedulesRequest;

public interface ISchedulesService {


    boolean putSchedule(int userId, int shopId, SchedulesRequest schedulesRequest);


    
}
