package com.buy_eat.buy_eat.model.response;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopDetailResponse {

    private Integer id;

    private String name;
    // private String phone;
    private String address;
    private String description;
    private String img;
    private String phone;
    private boolean isOrderable;
    private List<Schedule> schedules;

    // private int qty;

    // private int total;

    // private int totalOriginPrice;
    public ShopDetailResponse(Shop shop) {
        BeanUtils.copyProperties(shop, this);

        if (shop.getFileData() != null) {
            this.img = shop.getFileData().getFileName();
        }
        this.address = shop.getAddress().getCity() + shop.getAddress().getArea() + shop.getAddress().getDetail();
        List<Schedule> arrayList = new ArrayList<Schedule>();
        for (int i = 0; i < 7; i++) {
            arrayList.add(new Schedule(i));
        }
        
        arrayList.stream().forEach(v -> {
            List<TimePeriod> collect = shop.getSchedulesForOpen().stream()
                .filter(v2 -> v2.getWeek() == v.getWeek())
                .map(v3 -> new TimePeriod(v3.getStartTime(), v3.getEndTime()))
                .collect(Collectors.toList());

            v.setTimePeriods(collect);
        });
        this.schedules=arrayList;
    }

    public class Schedule {

        int week;

        List<TimePeriod> timePeriods;

        public Schedule(int week) {
            this.week = week;
        }

        public int getWeek() {
            return week;
        }

        public List<TimePeriod> getTimePeriods() {
            return timePeriods;
        }

        public void setTimePeriods(List<TimePeriod> timePeriods) {
            this.timePeriods = timePeriods;
        }

    }

    public class TimePeriod {

        @NonNull
        LocalTime startTime;

        @NonNull
        LocalTime endTime;

        TimePeriod(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime.truncatedTo(ChronoUnit.MINUTES);
            this.endTime = endTime.truncatedTo(ChronoUnit.MINUTES);
        }
        public LocalTime getStartTime() {
            return startTime;
        }
    
        public LocalTime getEndTime() {
            return endTime;
        }

    }

}
