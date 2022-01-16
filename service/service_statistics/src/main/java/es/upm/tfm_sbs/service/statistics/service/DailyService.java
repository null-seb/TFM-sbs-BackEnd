package es.upm.tfm_sbs.service.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.statistics.entity.Daily;

public interface DailyService extends IService<Daily> {
    void createStatisticsByDay(String day);
}
