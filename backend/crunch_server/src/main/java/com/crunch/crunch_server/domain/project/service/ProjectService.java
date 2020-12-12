package com.crunch.crunch_server.domain.project.service;

import java.util.ArrayList;
import java.util.List;

import com.crunch.crunch_server.domain.crew.entity.State;
import com.crunch.crunch_server.domain.crew.entity.WriterCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.WritersCrew;
import com.crunch.crunch_server.domain.crew.repository.WriterCrewRepository;
import com.crunch.crunch_server.domain.project.dto.CompletedPostListDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectStartDTO;

// import javax.print.event.PrintJobAdapter;

import com.crunch.crunch_server.domain.project.dto.ProjectTitleDdayDTO;
import com.crunch.crunch_server.domain.project.dto.RecruitingProjectListDTO;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.entity.Tag;
import com.crunch.crunch_server.domain.project.entity.TagIdentity;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.project.repository.TagRepository;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.domain.project.mapper.ProjectTitleDdayMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WriterCrewRepository writerCrewRepository;

    @Autowired
    private UserRepository userRepository;

    // @s

    // first main page
    public ProjectTitleDdayDTO getProjectTitleDday(int projectId) {
        Project project = repository.findById(projectId).orElse(null);
        // String projectTitle = project.getTitle();
        // String projectDday = project.getTarget_d_day();

        return ProjectTitleDdayMapper.Instance.toProjectTitleDdayDTO(project);
    }

    public int addProject(ProjectStartDTO projectStartDTO, int userId) {

        Project project = new Project();
        project.setTitle(projectStartDTO.getTitle());
        project.setIntroduction(projectStartDTO.getIntroduction());
        project.setMwn(projectStartDTO.getMwn());
        project.setTarget_d_day(projectStartDTO.getTarget_d_day());
        project.setTarget_funding_money(projectStartDTO.getTarget_funding_money());
        project.setState("collecting_writers");

        int projectId = repository.save(project).getId();
        System.out.println("----------------gettag------------------");
        System.out.println(projectStartDTO.getTags());
        // for (String dtoTag : projectStartDTO.getTags()) {
        for (int i = 0; i < projectStartDTO.getTags().size(); i++) {
            Tag tagEntity = new Tag();
            TagIdentity tIdentity = new TagIdentity();
            tIdentity.setProjectId(projectId);
            tagEntity.setTagIdentity(tIdentity);
            tagEntity.setText(projectStartDTO.getTags().get(i));
            tagRepository.save(tagEntity);

        }

        // 메인작가 selected로 바꿔주기
        WritersCrew mainWriter = new WritersCrew();
        WriterCrewIdentity writerIdentity = new WriterCrewIdentity();
        mainWriter.setMainornot(1);
        mainWriter.setState(State.selected);
        writerIdentity.setProjectId(projectId);
        writerIdentity.setUserId(userId);
        mainWriter.setWriterCrewIdentity(writerIdentity);

        writerCrewRepository.save(mainWriter);

        return projectId;
    }

    public ProjectStartDTO getRecruitingProjectInfo(int id) {
        Project project = repository.findById(id);
        ProjectStartDTO projectStartDTO = new ProjectStartDTO();

        projectStartDTO.setTitle(project.getTitle());
        projectStartDTO.setIntroduction(project.getIntroduction());
        projectStartDTO.setMwn(project.getMwn());
        projectStartDTO.setTarget_d_day(project.getTarget_d_day());
        projectStartDTO.setTarget_funding_money(project.getTarget_funding_money());

        return projectStartDTO;
    }

    public void changeProjectStateWriting(int projectId) {
        System.out.println("------------");
        System.out.println(projectId);
        Project project = repository.findById(projectId);
        project.setState("writing");

        repository.save(project);

    }

    // List<CompletedPostListDTO>
    public List<CompletedPostListDTO> getProjectListOfSelectedTag(String tagText) {
        System.out.println(tagText);
        List<Integer> projectIdList = new ArrayList<Integer>();
        List<Tag> tList = new ArrayList<Tag>();
        // tList = tagRepository.findByText(tagText);
        tList = tagRepository.findByText(tagText);

        System.out.println("=======*=======*=======*=======*=======");
        for (Tag tagEntity : tList) {
            System.out.println(tagEntity.getTagIdentity().getProjectId());
            projectIdList.add(tagEntity.getTagIdentity().getProjectId());
        }

        // 완료된 프로젝트 리스트 반환하기
        List<CompletedPostListDTO> cDtos = new ArrayList<CompletedPostListDTO>();
        for (Integer projectId : projectIdList) {
            CompletedPostListDTO CompleteProject = new CompletedPostListDTO();

            // 제목
            Project project = repository.findByIdAndState(projectId.intValue(), "whole_complete");
            if (project == null)
                continue;
            System.out.println(project.getTitle());
            CompleteProject.setTitle(project.getTitle());

            // 작가목록
            // String a = "selected";
            // State state = new State();
            // State writerState = new State();
            List<WritersCrew> wCrews = writerCrewRepository
                    .findByWriterCrewIdentityProjectIdAndState(projectId.intValue(), State.selected);
            List<String> writersNameList = new ArrayList<String>();
            for (WritersCrew wCrew : wCrews) {
                User user = userRepository.findById(wCrew.getWriterCrewIdentity().getUserId());
                System.out.println(user.getNickname());
                writersNameList.add(user.getNickname());
            }
            CompleteProject.setWriterList(writersNameList);

            // 좋아요
            CompleteProject.setLikeNum(0);

            cDtos.add(CompleteProject);
        }
        return cDtos;
    }

    public List<RecruitingProjectListDTO> getRecruitingProjectListOfSelectedTag(String genre) {

        List<Integer> projectIdList = new ArrayList<Integer>();
        List<Tag> tList = new ArrayList<Tag>();
        // tList = tagRepository.findByText(tagText);
        tList = tagRepository.findByText(genre);

        for (Tag tagEntity : tList) {
            System.out.println(tagEntity.getTagIdentity().getProjectId());
            projectIdList.add(tagEntity.getTagIdentity().getProjectId());
        }

        List<RecruitingProjectListDTO> rList = new ArrayList<RecruitingProjectListDTO>();

        for (Integer projectId : projectIdList) {
            // System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
            // System.out.println(projectId.intValue());
            Project project = repository.findByIdAndState(projectId.intValue(), "collecting_writers");
            if (project == null)
                continue;
            System.out.println("=======*=======*=======*=======*=======");
            System.out.println(project.getTitle());
            System.out.println("=======*=======*=======*=======*=======");
            RecruitingProjectListDTO rDto = new RecruitingProjectListDTO();

            List<WritersCrew> wCrews = writerCrewRepository.findByStateAndWriterCrewIdentityProjectId(State.ing,
                    projectId.intValue());
            WritersCrew mainWriter = writerCrewRepository.findByMainornotAndWriterCrewIdentityProjectId(1,
                    projectId.intValue());
            User user = userRepository.findById(mainWriter.getWriterCrewIdentity().getUserId());
            rDto.setApplyingNum(wCrews.size());
            rDto.setMainWriter(user.getNickname());

            rDto.setRecruitingNum(project.getMwn());
            rDto.setTargetDDay(project.getTarget_d_day());
            rDto.setTitle(project.getTitle());

            rList.add(rDto);
        }

        return rList;
    }

}
