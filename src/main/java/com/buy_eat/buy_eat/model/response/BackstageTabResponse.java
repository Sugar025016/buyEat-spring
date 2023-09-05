package com.buy_eat.buy_eat.model.response;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Tab;

import lombok.Getter;
import lombok.Setter;

public class BackstageTabResponse {
    

    @Getter
    @Setter
    public class Tabs {

        private Integer id;

        private String name;

        public Tabs() {

        }

        public Tabs(Tab tab) {
            BeanUtils.copyProperties(tab, this);

        }
    }
}
