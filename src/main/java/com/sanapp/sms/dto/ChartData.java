package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Month;

@Setter
@Getter
@Builder
public class ChartData {
    private Month month;
    private Double amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChartData)) return false;

        ChartData chartData = (ChartData) o;

        return getMonth() == chartData.getMonth();
    }

    @Override
    public int hashCode() {
        return getMonth() != null ? getMonth().hashCode() : 0;
    }
}
