package com.jdc.join.model.dto;

import com.jdc.join.model.entity.constants.WholesaleType;

public record BookByWholesalePrice(String name, WholesaleType wsType, double wsPrice) {

}
