package es.upm.tfm_sbs.service.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.statistics.entity.Daily;

import java.util.Map;

public interface DailyService extends IService<Daily> {
    void createStatisticsByDay(String day);

    Map<String, Map<String, Object>> getChartData(String begin, String end);
}
