package com.ssafy.api.service;

import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.ActivityRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;
    private final ActivityRepositorySupport activityRepositorySupport;

    public ActivityService(ActivityRepository activityRepository, ActivityRepositorySupport activityRepositorySupport){
        this.activityRepository = activityRepository;
        this.activityRepositorySupport = activityRepositorySupport;
    }

    public Page<Activity> getAvtivityList(Pageable pageable) {

        Page<Activity> activities = activityRepositorySupport.findAll(pageable);

        if(activities.isEmpty()) return null;

        return activities;
    }

    
    
    
    //메이트 등록
    public int createActivity(ActivityPostReq activityInfo) {

        Activity activity = Activity.builder()
                .categoryId(activityInfo.getCategoryId())
                .isTeam(activityInfo.isTeam())
                .createId(activityInfo.getCreateId())
                .meetDt(activityInfo.getMeetDt())
                .title(activityInfo.getTitle())
                .description(activityInfo.getDescription())
                .build();
                
        if(activity == null) return 500;

        activityRepository.save(activity);
        return 200;
    }



    public Activity getAvtivityDetail(Long activityId) {

        return activityRepository.findById(activityId).orElse(null);
    }
}
