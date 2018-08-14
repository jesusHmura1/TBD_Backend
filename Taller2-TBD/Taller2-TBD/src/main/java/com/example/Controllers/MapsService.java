package com.example.Controllers;


import com.example.Entities.Maps;
import com.example.Entities.Region;
import com.example.Entities.Statistics;
import com.example.Entities.dto.RegionStatisticsDTO;
import com.example.Repositories.CommuneRepository;
import com.example.Repositories.MapsRepository;
import com.example.Repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/maps")
public class MapsService {

    @Autowired
    private MapsRepository mapsRepository;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Maps> getAllMaps() {
        List<Maps> maps= mapsRepository.findAll();
        maps.sort(Comparator.comparing(Maps::getId));

        ArrayList<Maps> mapsFinal= new ArrayList<Maps>();

        for (int i=maps.size()-1;i>maps.size()-16;i--){
            mapsFinal.add(maps.get(i));
        }
        return mapsFinal;
    }

    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getAllRegions() {
        Region region = new Region();
        List<Region>  regiones = regionRepository.findAll();return regiones;

    }

    @RequestMapping(value = "/regions/statistic", method = RequestMethod.GET)
    @ResponseBody
    public RegionStatisticsDTO getAllRegionStatistics() {
        Statistics est = statisticService.crearEstadistica("Los mas weones",2,5,3);
        RegionStatisticsDTO regionEstadistica = new RegionStatisticsDTO();
        regionEstadistica.setStatistic(est);
        Region region = regionRepository.findAll().get(0);

        regionEstadistica.setRegion(region);
        return regionEstadistica;




    }
}
