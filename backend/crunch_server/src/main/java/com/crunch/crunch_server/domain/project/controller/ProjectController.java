// package com.crunch.crunch_server.domain.project.controller;
package com.crunch.crunch_server.domain.project.controller;

import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.crunch.crunch_server.domain.crew.dto.WriterListDTO;
import com.crunch.crunch_server.domain.crew.dto.WriterMoneyPercentDTO;
import com.crunch.crunch_server.domain.crew.service.WriterCrewService;
import com.crunch.crunch_server.domain.project.dto.CompletedPostListDTO;
import com.crunch.crunch_server.domain.project.dto.GenreDTO;
import com.crunch.crunch_server.domain.project.dto.MyWritingDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIdDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectStartDTO;
import com.crunch.crunch_server.domain.project.dto.RecruitingProjectListDTO;
import com.crunch.crunch_server.domain.project.dto.SetIndexFeeDTO;
import com.crunch.crunch_server.domain.project.dto.TmpDTO;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.service.*;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private WriterCrewService writerCrewservice;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/project/startup")
    @ResponseStatus(value = HttpStatus.OK)
    public int addProject(@RequestHeader(value = "token") String token, @RequestBody ProjectStartDTO projectStartDTO) {
        // System.out.println(projectStartDTO.getTitle());
        // 프로젝트를 save
        System.out.println("heeloo");
        // writerscrew에 메인작가로 등록
        int userId = jwtUtil.getUserId(token);

        int projectId = service.addProject(projectStartDTO, userId);
        writerCrewservice.addMainWriter(userId, projectId);
        return projectId;
    }

    // @CrossOrigin(origins = "*")
    // @PostMapping("/project/startup/banner")
    // @ResponseStatus(value = HttpStatus.OK)
    // public void a(@RequestParam("photo") MultipartFile file) throws Exception {
    // String rootPath =
    // FileSystemView.getFileSystemView().getHomeDirectory().toString();
    // String basePath = rootPath + "/" + "Temp";

    // String filePath = basePath + "/" + file.getOriginalFilename();

    // File dest = new File(filePath);
    // photo.transferTo(dest);

    // }

    @CrossOrigin(origins = "*")
    @PostMapping("/project/startup/banner")
    @ResponseStatus(value = HttpStatus.OK)
    public int a(@RequestParam("img") MultipartFile files) throws Exception {
        String rootPath = System.getProperty("user.dir");

        System.out.println("현재 프로젝트의 경로 : " + rootPath);

        // String baseDir = "C:\\Users\\hyejin";
        // String rootPath =
        // FileSystemView.getFileSystemView().getHomeDirectory().toString();
        // System.out.println(rootPath);
        // String basePath = rootPath + "/" + "Temp";
        // String filePath = basePath + "/" + files.getOriginalFilename();

        String filePath = rootPath + "\\backend\\crunch_server\\src\\main\\resources\\static\\img\\"
                + files.getOriginalFilename();
        // String filePath = "C:/Temp/" + files.getOriginalFilename();
        files.transferTo(new File(filePath));

        return 100;
    }

    // @CrossOrigin(origins = "*")
    // @GetMapping("/display")
    // @ResponseStatus(value = HttpStatus.OK)
    // public int a(@RequestParam("img") MultipartFile files) throws Exception {

    // }

    @CrossOrigin(origins = "*")
    @PostMapping("/collaboProj")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectStartDTO getRecruitingCollaboProj(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);

        System.out.println(projectIdDTO.getId());

        // project id로 프로젝트 펀딩금액, 목표 작업기한, 모집 작가 수, 소개 가져오기
        // service.getRecruitingProjectInfo(projectIdDTO.getId());
        System.out.println("heeloo");

        // return service.addProject(projectStartDTO);
        return service.getRecruitingProjectInfo(projectIdDTO.getId());
    }

    // List<CompletedPostListDTO>
    @CrossOrigin(origins = "*")
    @PostMapping("/getpostlist")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompletedPostListDTO> getPostListOfSelectedGenre(@RequestHeader(value = "token") String token,
            @RequestBody GenreDTO genreDTO) {

        // int userId = jwtUtil.getUserId(token);
        System.out.println("====================================");
        System.out.println(genreDTO.getGenre());
        // tagDTO.getTagText()
        return service.getProjectListOfSelectedTag(genreDTO.getGenre());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getrecruitingPost")
    @ResponseStatus(value = HttpStatus.OK)
    public List<RecruitingProjectListDTO> getRecruitingPostList(@RequestHeader(value = "token") String token,
            @RequestBody GenreDTO genreDTO) {

        return service.getRecruitingProjectListOfSelectedTag(genreDTO.getGenre());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/mypageWritingProjectList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MyWritingDTO> getMypageWritingList(@RequestHeader(value = "token") String token,
            @RequestBody TmpDTO tmpDTO) {

        int userId = jwtUtil.getUserId(token);
        return service.getMyPageWritingProjectList(userId);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/mypageCompleteProjectList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MyWritingDTO> getMypageCompleteProject(@RequestHeader(value = "token") String token,
            @RequestBody TmpDTO tmpDTO) {

        int userId = jwtUtil.getUserId(token);
        return service.getMypageCompleteProjectList(userId);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/endRecruitingStartProject")
    @ResponseStatus(value = HttpStatus.OK)
    public void endRecruitingStartProject(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);
        service.changeProjectStateToWriting(projectIdDTO.getId());

    }

    // 수익 정산 부분 작가목록 가져오기
    @CrossOrigin(origins = "*")
    @PostMapping("/getWriterList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<WriterListDTO> getWriterList(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);
        List<WriterListDTO> writerListDTOs = service.getWritersList(projectIdDTO.getId());

        return writerListDTOs;

    }

    // 수익 정산 부분 목차 가져오기
    @CrossOrigin(origins = "*")
    @PostMapping("/getIndexList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostIndex> getIndexList(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);

        List<PostIndex> indexList = service.getTitleList(projectIdDTO.getId());
        return indexList;

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/submitWriterMoneyPercent/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void endProjectWriter(@RequestHeader(value = "token") String token,
            @RequestBody List<WriterMoneyPercentDTO> wPercentDTOs, @PathVariable int projectId) {

        int userId = jwtUtil.getUserId(token);
        System.out.println("====================================");

        service.setWritersMoneyPercent(wPercentDTOs, projectId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/submitIndexProfit/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void endProjectIndex(@RequestHeader(value = "token") String token, @RequestBody List<SetIndexFeeDTO> iDtos,
            @PathVariable int projectId) {

        int userId = jwtUtil.getUserId(token);
        System.out.println("====================================");

        service.setPostIndexFee(iDtos, projectId);

        service.changeProjectStateToWholeComplete(projectId);

        service.lastSubmitToCompletePost(projectId);
    }

}
