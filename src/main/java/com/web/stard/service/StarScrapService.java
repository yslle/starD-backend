package com.web.stard.service;

import com.web.stard.domain.*;
import com.web.stard.repository.StarScrapRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
public class StarScrapService {

    MemberService memberService;
    CommunityService communityService;
    StudyService studyService;
    StarScrapRepository starScrapRepository;


    /* Post(community) Star 여부 확인 */
    public StarScrap existsCommStar(Member member, Post post) {
        Optional<StarScrap> star = starScrapRepository.findByMemberAndPostAndTypeAndPostType(member, post, ActType.STAR, PostType.COMM);

        if (star.isPresent()) {
            return star.get();
        } return null;
    }

    /* 공감한 Post(community) List 조회 */
    public List<StarScrap> allPostStarList(Authentication authentication) {
        Member member = memberService.find(authentication.getName());
        return starScrapRepository.findAllByMemberAndTypeAndPostType(member, ActType.STAR, PostType.COMM);
    }


    /* Post(community) 공감 추가 */
    public StarScrap addPostStar(Long id, Authentication authentication) {
        Post post = communityService.getCommunityPost(id);
        Member member = memberService.find(authentication.getName());

        // 이미 존재하는지 확인 (혹시 모를 중복 저장 방지)
        StarScrap star = existsCommStar(member, post);
        if (star != null) {
            return star;
        }

        star = StarScrap.builder()
                .post(post)
                .type(ActType.STAR)
                .tableType(PostType.COMM)
                .member(member)
                .build();

        starScrapRepository.save(star);

        return star;
    }

    /* Post(community) 공감 삭제 */
    public boolean deletePostStar(Long id, Authentication authentication) {
        Post post = communityService.getCommunityPost(id);
        Member member = memberService.find(authentication.getName());
        StarScrap star = existsCommStar(member, post);

        if (star == null) { // 혹시 모를 오류 방지
            return false;
        }

        starScrapRepository.delete(star);

        star = existsCommStar(member, post);
        if (star == null) {
            return true;
        } return false;
    }



    /* Study Star 여부 확인 */
    public StarScrap existsStudyStar(Member member, Study study) {
        Optional<StarScrap> star = starScrapRepository.findByMemberAndStudyAndTypeAndPostType(member, study,ActType.STAR, PostType.STUDY);

        if (star.isPresent()) {
            return star.get();
        } return null;
    }

    /* 공감한 Study List 조회 */
    public List<StarScrap> allStudyStarList(Authentication authentication) {
        Member member = memberService.find(authentication.getName());
        return starScrapRepository.findAllByMemberAndTypeAndPostType(member,ActType.STAR, PostType.STUDY);
    }

    /* Study 공감 추가 */
    public StarScrap addStudyStar(Long id, Authentication authentication) {
        Study study = studyService.findById(id);
        Member member = memberService.find(authentication.getName());

        // 이미 존재하는지 확인 (혹시 모를 중복 저장 방지)
        StarScrap star = existsStudyStar(member, study);
        if (star != null) {
            return star;
        }

        star = StarScrap.builder()
                .study(study)
                .type(ActType.STAR)
                .tableType(PostType.STUDY)
                .member(member)
                .build();

        starScrapRepository.save(star);

        return star;
    }

    /* Study 공감 삭제 */
    public boolean deleteStudyStar(Long id, Authentication authentication) {
        Study study = studyService.findById(id);
        Member member = memberService.find(authentication.getName());
        StarScrap star = existsStudyStar(member, study);

        if (star == null) { // 혹시 모를 오류 방지
            return false;
        }

        starScrapRepository.delete(star);

        star = existsStudyStar(member, study);
        if (star == null) {
            return true;
        } return false;
    }






    /* Post(community) Scrap 여부 확인 */
    public StarScrap existsCommScrap(Member member, Post post) {
        Optional<StarScrap> scrap = starScrapRepository.findByMemberAndPostAndTypeAndPostType(member, post, ActType.SCRAP, PostType.COMM);

        if (scrap.isPresent()) {
            return scrap.get();
        } return null;
    }

    /* 스크랩한 Post(community) List 조회 */
    public List<StarScrap> allPostScrapList(Authentication authentication) {
        Member member = memberService.find(authentication.getName());
        return starScrapRepository.findAllByMemberAndTypeAndPostType(member, ActType.SCRAP, PostType.COMM);
    }

    /* Post(community) Scrap 추가 */
    public StarScrap addPostScrap(Long id, Authentication authentication) {
        Post post = communityService.getCommunityPost(id);
        Member member = memberService.find(authentication.getName());

        // 이미 존재하는지 확인 (혹시 모를 중복 저장 방지)
        StarScrap scrap = existsCommScrap(member, post);
        if (scrap != null) {
            return scrap;
        }

        scrap = StarScrap.builder()
                .post(post)
                .type(ActType.SCRAP)
                .tableType(PostType.COMM)
                .member(member)
                .build();

        starScrapRepository.save(scrap);

        return scrap;
    }

    /* Scrap 공감 삭제 */
    public boolean deletePostScrap(Long id, Authentication authentication) {
        Post post = communityService.getCommunityPost(id);
        Member member = memberService.find(authentication.getName());
        StarScrap scrap = existsCommScrap(member, post);

        if (scrap == null) { // 혹시 모를 오류 방지
            return false;
        }

        starScrapRepository.delete(scrap);

        scrap = existsCommScrap(member, post);
        if (scrap == null) {
            return true;
        } return false;
    }



    /* Study Scrap 여부 확인 */
    public StarScrap existsStudyScrap(Member member, Study study) {
        Optional<StarScrap> scrap = starScrapRepository.findByMemberAndStudyAndTypeAndPostType(member, study, ActType.SCRAP, PostType.STUDY);

        if (scrap.isPresent()) {
            return scrap.get();
        } return null;
    }

    /* 스크랩한 Study List 조회 */
    public List<StarScrap> allStudyScrapList(Authentication authentication) {
        Member member = memberService.find(authentication.getName());
        return starScrapRepository.findAllByMemberAndTypeAndPostType(member, ActType.SCRAP, PostType.STUDY);
    }

    /* Study Scrap 추가 */
    public StarScrap addStudyScrap(Long id, Authentication authentication) {
        Study study = studyService.findById(id);
        Member member = memberService.find(authentication.getName());

        // 이미 존재하는지 확인 (혹시 모를 중복 저장 방지)
        StarScrap scrap = existsStudyScrap(member, study);
        if (scrap != null) {
            return scrap;
        }

        scrap = StarScrap.builder()
                .study(study)
                .type(ActType.SCRAP)
                .tableType(PostType.STUDY)
                .member(member)
                .build();

        starScrapRepository.save(scrap);

        return scrap;
    }

    /* Study Scrap 삭제 */
    public boolean deleteStudyScrap(Long id, Authentication authentication) {
        Study study = studyService.findById(id);
        Member member = memberService.find(authentication.getName());
        StarScrap scrap = existsStudyScrap(member, study);

        if (scrap == null) { // 혹시 모를 오류 방지
            return false;
        }

        starScrapRepository.delete(scrap);

        scrap = existsStudyScrap(member, study);
        if (scrap == null) {
            return true;
        } return false;
    }
}
