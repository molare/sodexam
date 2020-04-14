package renting.com.service;

import java.util.List;

/**
 * Created by olivier on 20/12/2019.
 */
public interface DashbordService {
    List<Object> firstYearPayRollChart();
    List<Object> secondYearPayRollChart();
    List<Object> threeYearPayRollChart();
}
