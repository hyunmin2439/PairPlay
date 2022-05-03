package com.ssafy.api.service;

import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.ActivityRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public Page<Activity> getCategoryList(ActivityCategoryReq activityCategoryReq, Pageable pageable) {

        Page<Activity> activities = null;

        if(activityCategoryReq.getCategoryId()!=0 && !activityCategoryReq.getLocation().equals("")) {//운동이랑 위치 값 넘어왔을 때
            activities = activityRepositorySupport.findByCategory(pageable, activityCategoryReq.getCategoryId(), activityCategoryReq.getLocation());
        }else if(activityCategoryReq.getCategoryId()!=0 && activityCategoryReq.getLocation().equals("")){//운동만 넘어왔을 때
            activities = activityRepositorySupport.findByCategory(pageable, activityCategoryReq.getCategoryId());
        }else if(activityCategoryReq.getCategoryId()==0 && !activityCategoryReq.getLocation().equals("")) {//지역만 넘어왔을 때
            activities = activityRepositorySupport.findByCategory(pageable, activityCategoryReq.getLocation());
        }

        if(activities.isEmpty()) return null;

        return activities;
    }
    
    
    //메이트 등록
    public int createActivity(ActivityPostReq activityInfo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Activity activity = Activity.builder()
                .categoryId(activityInfo.getCategoryId())
                .createId(memberId)
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
