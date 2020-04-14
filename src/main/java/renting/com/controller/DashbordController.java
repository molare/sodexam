package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import renting.com.dataTableResponse.ResponseData;
import renting.com.service.DashbordService;

import java.util.List;

/**
 * Created by olivier on 21/12/2019.
 */
@RestController
public class DashbordController {
    @Autowired
    private DashbordService dashbordService;
    @RequestMapping(value = "/payRoll/charts", method = RequestMethod.POST)
    public ResponseData saleCharts(){
        List<Object>  firstPayRollCharts = dashbordService.firstYearPayRollChart();
        List<Object>  secondPayRollCharts = dashbordService.secondYearPayRollChart();
        List<Object> threePayRollCharts = dashbordService.threeYearPayRollChart();
        return new ResponseData(true,firstPayRollCharts,secondPayRollCharts,threePayRollCharts);
    }
}
