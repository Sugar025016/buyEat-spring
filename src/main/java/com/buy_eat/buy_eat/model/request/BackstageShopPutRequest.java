package com.buy_eat.buy_eat.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BackstageShopPutRequest {

    @NotNull
    private Integer id;

    private String shopName;

    @Size(min=10,max=11)
    private String phone;

    @Size(min=8,max=255)
    private String description;

    private AddressRequest address;

    @NotNull
    private Integer imgId;

    private boolean isOrderable;
    private boolean isDisable;
    private boolean isDelete;
}
