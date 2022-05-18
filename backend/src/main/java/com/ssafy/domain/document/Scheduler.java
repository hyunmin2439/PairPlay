package com.ssafy.domain.document;

import com.ssafy.api.service.ActivityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private ActivityService activityService;
    public Scheduler(ActivityService activityService){
        this.activityService = activityService;
    }

    @Scheduled(cron = "0 6 35 * * ?")
    public void cronJobSch(){
        activityService.endActivity();

    }
}
