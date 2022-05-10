package com.ssafy.api.service;

import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.request.ActivityRegisterReq;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.ActivityRepositorySupport;
import com.ssafy.domain.repository.MateRepository;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;
    private final ActivityRepositorySupport activityRepositorySupport;
    private final MemberRepository memberRepository;
    private final MateRepository mateRepository;

    public ActivityService(ActivityRepository activityRepository, ActivityRepositorySupport activityRepositorySupport, MemberRepository memberRepository, MateRepository mateRepository){
        this.activityRepository = activityRepository;
        this.activityRepositorySupport = activityRepositorySupport;
        this.memberRepository = memberRepository;
        this.mateRepository = mateRepository;
    }

    public Page<Activity> getActivityList(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if(member != null) {
            if (member.getSido() != null || member.getGugun() != null) {
                String location = member.getSido() + " " + member.getGugun();
                System.out.println(location);
                return activityRepositorySupport.findAllByLocation(pageable, location);
            }
        }

        return activityRepositorySupport.findAll(pageable);

    }


    public Page<Activity> getCategoryList(ActivityCategoryReq activityCategoryReq, Pageable pageable) {

        Page<Activity> activities = null;
        String location = null, sido = null, gugun = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        assert member != null;
        sido = member.getSido();
        gugun = member.getGugun();

        if(!activityCategoryReq.getSido().equals("") || !activityCategoryReq.getGungu().equals("")){

            sido = activityCategoryReq.getSido();
            gugun = activityCategoryReq.getGungu();
        }

        location = sido + " " + gugun;



        /*
         * 운동 카테고리, 검색어
         */
        if(activityCategoryReq.getCategoryId()!=0 && !activityCategoryReq.getSearch().equals("")){

            activities = activityRepositorySupport.findByCategorySearch(pageable, location, activityCategoryReq.getCategoryId(), activityCategoryReq.getSearch());

        }

        /*
         * 운동 카테고리
         */
        else if(activityCategoryReq.getCategoryId()!=0){

            activities = activityRepositorySupport.findByCategory(pageable, location, activityCategoryReq.getCategoryId());

        }

        /*
         * 검색어
         */
        else if(!activityCategoryReq.getSearch().equals("")) {

            activities = activityRepositorySupport.findByCategorySearch(pageable, location, activityCategoryReq.getSearch());
        }
        /*
         * 지역만 검색
         */
        else{

            activities = activityRepositorySupport.findByCategorySearch(pageable, location);
        }



        return  activities;
    }
    
    
    //메이트 등록
    public void createActivity(ActivityPostReq activityInfo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());



        Activity activity = Activity.builder()
                .categoryId(activityInfo.getCategoryId())
                .createId(memberId)
                .meetDt(activityInfo.getMeetDt())
                .title(activityInfo.getTitle())
                .description(activityInfo.getDescription())
                .isEnd(false)
                .build();

        activityRepository.save(activity);
    }


    //메이트 상세 조회
    public Activity getActivityDetail(Long activityId) {

        return activityRepository.findById(activityId).orElse(null);
    }



    //메이트 신청
    public void registerActivity(ActivityRegisterReq req) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);
        Activity activity = activityRepository.findById(req.getActivityId()).orElse(null);
        if(req.getActivityId() == null || member == null){
            throw new CustomException(EMPTY_REQUEST_VALUE);
        }

        Mate mate = Mate.builder()
                .activityId(activity)
                .memberId(member)
                .build();

        mateRepository.save(mate);
    }
}
