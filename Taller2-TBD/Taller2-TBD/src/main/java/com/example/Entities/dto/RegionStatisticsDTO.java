package com.example.Entities.dto;

import com.example.Entities.Region;
import com.example.Entities.Statistics;

public class RegionStatisticsDTO {


    Region region;

    Statistics statistic;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Statistics getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistics statistic) {
        this.statistic = statistic;
    }
}
