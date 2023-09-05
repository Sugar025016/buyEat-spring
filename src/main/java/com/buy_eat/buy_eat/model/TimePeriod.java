package com.buy_eat.buy_eat.model;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class TimePeriod {



//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    LocalTime startTime;

//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    LocalTime endTime;



    // public void setStartTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
    //     this.startTime = new java.sql.Time(date.getTime());
    // }

    // public void setEndTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {

    //     this.endTime = new java.sql.Time(date.getTime());
    // }


}
