package com.fzu.stop.service;

import com.fzu.stop.pojo.DeviceInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class StatisticsServiceImplTest {
    @Autowired
    DeviceService deviceService;
    @Autowired
    StatisticsService statisticsService;

    @Test
    public void getAllPoints() {
        List<DeviceInfoDTO> deviceInfoDTOList = deviceService.listAllDevice();
        boolean hasOnline = false;
        for (DeviceInfoDTO deviceInfoDTO : deviceInfoDTOList) {
            if (deviceInfoDTO.getOnline()) {
                hasOnline = true;
                break;
            }
        }
        assertEquals(hasOnline,statisticsService.getAllPoints().size() > 0);
    }
}