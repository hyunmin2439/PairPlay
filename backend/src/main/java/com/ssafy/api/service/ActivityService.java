package com.ssafy.api.service;


import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.request.ActivityRegisterReq;
import com.ssafy.api.response.ActivityListRes;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.ActivityLike;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ssafy.common.statuscode.ActivityCode.*;
import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;
    private final ActivityRepositorySupport activityRepositorySupport;
    private final MemberRepository memberRepository;
    private final MateRepository mateRepository;
    private final ActivityLikeRepository activityLikeRepository;

    public ActivityService(ActivityRepository activityRepository,
                           ActivityRepositorySupport activityRepositorySupport,
                           MemberRepository memberRepository,
                           MateRepository mateRepository,
                           ActivityLikeRepository activityLikeRepository){
        this.activityRepository = activityRepository;
        this.activityRepositorySupport = activityRepositorySupport;
        this.memberRepository = memberRepository;
        this.mateRepository = mateRepository;
        this.activityLikeRepository = activityLikeRepository;
    }

    public Member findId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(FAIL_MEMBER_NOT_FOUND));
    }

    @Transactional
    public ActivityListRes getActivityList(Pageable pageable) {

        Member member = findId();

        if(member != null) {
            if (member.getSido() != null || member.getGugun() != null) {
                String location = member.getSido() + " " + member.getGugun();
                System.out.println(location);
                Page<Mate> mates = activityRepositorySupport.findAllByLocation(pageable, location);
                return ActivityListRes.of(mates, SUCCESS_MATE_LIST.getCode(), SUCCESS_MATE_LIST.getMessage());
            }
        }

        Page<Mate> mates = activityRepositorySupport.findAllByActivityId_CreateIdEqualsMemberId_Id(pageable);


        return ActivityListRes.of(mates, SUCCESS_MATE_LIST.getCode(), SUCCESS_MATE_LIST.getMessage());
//        return activityRepositorySupport.findAll(pageable);
    }

    @Transactional
    public ActivityListRes getCategoryList(ActivityCategoryReq activityCategoryReq, Pageable pageable) {

        Page<Mate> activities = null;
        String location = null, sido = null, gugun = null;

        Member member = findId();

        assert member != null;
        sido = member.getSido();
        gugun = member.getGugun();

        if(!activityCategoryReq.getSido().equals("") || !activityCategoryReq.getGungu().equals("")){

            sido = activityCategoryReq.getSido();
            gugun = activityCategoryReq.getGungu();
        }

        location = sido + " " + gugun;
//
//
//
//        /*
//         * 운동 카테고리, 검색어
//         */
        if(activityCategoryReq.getCategoryId()!=0 && !activityCategoryReq.getSearch().equals("")){
            activities = activityRepositorySupport.findByCategorySearch(pageable, location, activityCategoryReq.getCategoryId(), activityCategoryReq.getSearch());

        }

        /*
         * 운동 카테고리
         */
        else if(activityCategoryReq.getCategoryId()!=0){

            activities = activityRepositorySupport.findByCategory(pageable, activityCategoryReq.getCategoryId());

        }

        /*
         * 검색어
         */
        else if(!activityCategoryReq.getSearch().equals("")) {

            activities = activityRepositorySupport.findBySearch(pageable, activityCategoryReq.getSearch());
        }
        /*
         * 지역만 검색
         */
        else{

            activities = activityRepositorySupport.findAllByLocation(pageable, location);
        }


        return ActivityListRes.of(activities, SUCCESS_MATE_LIST.getCode(), SUCCESS_MATE_LIST.getMessage());
    }
    
    
    //메이트 등록
    public void createActivity(ActivityPostReq activityInfo, List<String> fileName) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(FAIL_MEMBER_NOT_FOUND));


        String fileNameArr = "";

        if(fileName.size() != 0){
            for (String file : fileName) {
                fileNameArr = fileNameArr + " " + file + " ";
            }
        }


        System.out.println("확인" + fileNameArr);



        Activity activity = Activity.builder()
                .categoryId(activityInfo.getCategoryId())
                .createId(memberId)
                .meetDt(activityInfo.getMeetDt())
                .title(activityInfo.getTitle())
                .description(activityInfo.getDescription())
                .location(activityInfo.getLocation())
                .mateImage(fileNameArr)
                .age(activityInfo.getAge())
                .gender(activityInfo.getGender())
                .closeDt(activityInfo.getCloseDt())
                .isEnd(false)
                .build();
        activityRepository.save(activity);

        Activity activityId = activityRepository.findTop1ByCreateIdOrderByIdDesc(memberId);

        Mate mate = Mate.builder()
                        .activityId(activityId)
                        .memberId(member)
                        .build();

        mateRepository.save(mate);

    }


    //메이트 상세 조회
    public Activity getActivityDetail(Long activityId) {
        return activityRepository.findById(activityId).orElseThrow(() -> new CustomException(FAIL_ACTIVITY_NOT_FOUND));
    }



    //메이트 신청
    public void registerActivity(ActivityRegisterReq req) {

        Member member = findId();
        Activity activity = activityRepository.findById(req.getActivityId()).orElseThrow(() -> new CustomException(FAIL_ACTIVITY_NOT_FOUND));
        if(req.getActivityId() == null || member == null){
            throw new CustomException(EMPTY_REQUEST_VALUE);
        }

        Mate mate = Mate.builder()
                .activityId(activity)
                .memberId(member)
                .build();

        mateRepository.save(mate);
    }



    //메이트 찜하기/취소
    @Transactional
    public void likeActivity(Long activityId) {


        Member member = findId();

        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new CustomException(FAIL_ACTIVITY_NOT_FOUND));

        Long id = null;
        for (ActivityLike like : member.getActivityLikeList()) {
            if (like.getActivityId().getId().equals(activityId)) {
                id = like.getId();
            }
        }
       
        if(id != null){
            activityLikeRepository.deleteById(id);
        }else{
           ActivityLike activityLike = ActivityLike.builder()
                   .activityId(activity)
                   .memberId(member)
                   .build();

           activityLikeRepository.save(activityLike);
        }


    }

    public void endActivity() {
        //end_dt가 오늘인거 가지고와서

        LocalDateTime closeDt = LocalDateTime.now();
        System.out.println("날짜확인" + closeDt);
        List<Activity> activity  = activityRepository.findByCloseDtBeforeAndIsEnd(closeDt, false);

        activity.forEach(date -> {
            System.out.println(date.getCloseDt());
            date.isEndUpdate(true);
            activityRepository.save(date);
        });



        //1으로 변환해주는 작업


    }
}
