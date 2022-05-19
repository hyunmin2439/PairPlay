package com.ssafy.api.service;


import com.ssafy.api.request.ActivityCategoryReq;
import com.ssafy.api.request.ActivityPostReq;
import com.ssafy.api.request.ActivityRegisterReq;
import com.ssafy.api.response.ActivityDetailRes;
import com.ssafy.api.response.ActivityListRes;
import com.ssafy.api.response.ActivityRes;
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


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.ActivityCode.*;
import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;
    private final ActivityRepositorySupport activityRepositorySupport;
    private final MemberRepository memberRepository;
    private final MateRepository mateRepository;
    private final ActivityLikeRepository activityLikeRepository;
    private final S3FileUploadService s3FileUploadService;
    public ActivityService(ActivityRepository activityRepository,
                           ActivityRepositorySupport activityRepositorySupport,
                           MemberRepository memberRepository,
                           MateRepository mateRepository,
                           ActivityLikeRepository activityLikeRepository,
                           S3FileUploadService s3FileUploadService){
        this.activityRepository = activityRepository;
        this.activityRepositorySupport = activityRepositorySupport;
        this.memberRepository = memberRepository;
        this.mateRepository = mateRepository;
        this.activityLikeRepository = activityLikeRepository;
        this.s3FileUploadService = s3FileUploadService;
    }

    public Member findId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(FAIL_MEMBER_NOT_FOUND));
    }

    @Transactional
    public ActivityListRes getActivityList(Pageable pageable) {

        Member member = findId();
        Long memberId = member.getId();
        Page<Mate> mates = null;

        if (member.getSido() != null || member.getGugun() != null) {
            String location = member.getSido() + " " + member.getGugun();
            System.out.println(location);
            mates = activityRepositorySupport.findAllByLocation(pageable, location);
        }

        List<ActivityRes> activityRes = new ArrayList<>();

        mates.forEach(mate -> {
            activityRes.add(ActivityRes.of(mate, memberId, s3FileUploadService.findImg(mate.getMemberId().getProfileImage())));
        });


        return ActivityListRes.of(mates.getTotalPages(), mates.getTotalElements(), activityRes);
    }

    @Transactional
    public ActivityListRes getCategoryList(ActivityCategoryReq activityCategoryReq, Pageable pageable) {

        Page<Mate> activities = null;
        String location = null, sido = null, gugun = null;

        Member member = findId();

        assert member != null;
        sido = member.getSido();
        gugun = member.getGugun();
        boolean isSearchLocation = true;
        //지역 값 없음
        if(!activityCategoryReq.getSido().equals("") || !activityCategoryReq.getGungu().equals("")){

            sido = activityCategoryReq.getSido();
            gugun = activityCategoryReq.getGungu();
            isSearchLocation = false;
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
        else if(activityCategoryReq.getCategoryId()!=0 && !isSearchLocation){
            activities = activityRepositorySupport.findByCategoryAndLocation(pageable, activityCategoryReq.getCategoryId(), location);
        }

        /*
         * 검색어 + 지역 안들어옴
         */
        else if(!activityCategoryReq.getSearch().equals("") && !isSearchLocation) {
            activities = activityRepositorySupport.findBySearchAndLocation(pageable, activityCategoryReq.getSearch(), location);
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

        List<ActivityRes> activityRes = new ArrayList<>();

        activities.forEach(mate -> {
            activityRes.add(ActivityRes.of(mate, member.getId() ,s3FileUploadService.findImg(mate.getMemberId().getProfileImage())));
        });


        return ActivityListRes.of(activities.getTotalPages(), activities.getTotalElements(), activityRes);
    }

    
    
    //공고 등록
    public void createActivity(ActivityPostReq activityInfo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(FAIL_MEMBER_NOT_FOUND));

        String location = activityInfo.getSido() + " " + activityInfo.getGugun();


        Activity activity = Activity.builder()
                .categoryId(activityInfo.getCategoryId())
                .createId(memberId)
                .meetDt(activityInfo.getMeetDt())
                .title(activityInfo.getTitle())
                .description(activityInfo.getDescription())
                .location(location)
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
                        .accept(1)
                        .build();

        mateRepository.save(mate);

    }


    //메이트 상세 조회
    @Transactional
    public ActivityDetailRes getActivityDetail(Long activityId) { //상세조회에서 이미지 사진

        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new CustomException(FAIL_ACTIVITY_NOT_FOUND));


        Mate mate = mateRepository.findByActivityId_IdAndMemberId_IdAndAccept(activity.getId(), activity.getCreateId(),1);

        if(mate.getMemberId().getProfileImage() == null){
            throw new CustomException(PROFILE_IMAGE_NULL);
        }


        String profileImage = s3FileUploadService.findImg(mate.getMemberId().getProfileImage());


        return ActivityDetailRes.of(mate, mate.getMemberId().getId(), profileImage);
    }



    //메이트 신청
    public void registerActivity(ActivityRegisterReq req) {

        Member member = findId();
        Activity activity = activityRepository.findById(req.getActivityId()).orElseThrow(() -> new CustomException(FAIL_ACTIVITY_NOT_FOUND));
        if(req.getActivityId() == null || member == null){
            throw new CustomException(EMPTY_REQUEST_VALUE);
        }else if(activity.getCreateId().equals(member.getId())){
            throw new CustomException(MATE_USER_REGISTER_SAME);
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
        LocalDateTime closeDt = LocalDateTime.now();

        List<Activity> activity  = activityRepository.findByCloseDtBeforeAndIsEnd(closeDt, false);

        activity.forEach(date -> {
            System.out.println(date.getCloseDt());
            date.isEndUpdate(true);
            activityRepository.save(date);
        });

    }
}
