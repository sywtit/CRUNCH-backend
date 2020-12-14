package com.crunch.crunch_server.domain.commit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.dto.CommitHistoryRevertDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.entity.PostLineDetail;
import com.crunch.crunch_server.domain.commit.entity.PostModification;
import com.crunch.crunch_server.domain.commit.mapper.CommitMapper;
import com.crunch.crunch_server.domain.commit.mapper.CommitPostModificationMapper;
import com.crunch.crunch_server.domain.commit.mapper.PostDetailMapper;
import com.crunch.crunch_server.domain.commit.repository.BlobRepository;
import com.crunch.crunch_server.domain.commit.repository.CommitDetailRepository;
import com.crunch.crunch_server.domain.commit.repository.ModifyCommitRepoistory;
import com.crunch.crunch_server.domain.commit.repository.ModifyPostModificationRepository;
import com.crunch.crunch_server.domain.project.entity.Posts;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.s3.S3Uploader;
import com.crunch.crunch_server.s3.S3Service;
import com.crunch.crunch_server.util.JwtUtil;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class ModifyService {
    
    @Autowired
    private ModifyCommitRepoistory commitRepoistory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PostService postService;
    @Autowired
    private BlobService blobService;
    @Autowired
    private ModifyPostModificationRepository postModificationRepository;

    @Autowired
    private S3Uploader s3Uploader;
    private S3Service s3Service;
    
    @Autowired
    private PostRepository postRespository;
    @Autowired
    private BlobRepository blobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommitDetailRepository commitDetailRepository;

    private MultipartFile[] wholeFiles;

    public void saveNewCommit(String token, int projectId, int indexId, ModifyDTO modifyDTO) throws Exception
    {
        //first get the user id from token
        int userId = jwtUtil.getUserId(token);

        //next get postId from the projectId and indexId
        int postId = postService.getPostID(projectId, indexId);

        //save new commit using userId and postID
        //1. get before post from blobservice's private data
        //2. set after post to blobservice's private data
        //3. get diff result from diff provider
        //4. get commit entity to save

        String before = blobService.getPost_now();

        String after = modifyDTO.getAfter().replace("</p>", "</p>\n");
        after = after.replace("</h1>", "</h1>\n");
        after = after.replace("</h2>", "</h2>\n");
        after = after.replace("</h3>", "</h3>\n");
        after = after.replace("</code>", "</code>\n");
        after = after.replace("</li>", "</li>\n");
        after = after.replace("</blockquote>", "</blockquote>\n");
        after = after.replace("</hr>", "</hr>\n");

//nothing changes
//-
//+


    //     String reg = "<img(?:.*?)>";
    //     String newTxt = after;

    //     Pattern pattern = Pattern.compile(reg);
    //     Matcher matcher = pattern.matcher(after);

    //     int fileCount  = 0;
    //    // List<MultipartFile> files = modifyDTO.getFiles();
        
    //     System.out.println(wholeFiles.length);

    //     for (MultipartFile uploadFile : wholeFiles) {
            
    //         matcher.find();

    //         String originalfileName = uploadFile.getOriginalFilename();


    //         //cut exe
    //         String ext = originalfileName.substring(originalfileName.indexOf("."));
    //         //reName

    //         //get recent commitId
    //         List<Commits> commits = blobRepository.findByPostId(postId);
    //         int last  = commits.size() -1;
    //         Commits recentCommit = (Commits)commits.get(last);

            
    //         String reName =  Integer.toString(recentCommit.getCommitId())+"_"+
    //         Integer.toString(fileCount)+ext;

        


    //       //  uploadFile.transferTo(Paths.get(reName));
         
    //     //  //   uploadFile.getOriginalFilename().replace(originalfileName, reName);

    //     //    byte[] data = uploadFile.getBytes();
    //     //    FileOutputStream fos = new FileOutputStream("C:/Temp"+ "/" + reName);
    //     //    fos.write(data);
    //     //    fos.close();

    //       //  String s3URL = s3Uploader.upload(uploadFile, "project/work");
    //         String s3URL = s3Service.upload(uploadFile, "project/work");

    //         System.out.println(s3URL);

    //         String matchStr = matcher.group(0);
    //         String beforeTxt = newTxt.substring(0, matcher.start(0));
    //         String afterTxt = newTxt.substring(matcher.end(0));
    //         matchStr = matchStr.replaceAll(matchStr, "<img src=\"blob:" + s3URL + ">");
    //         newTxt = beforeTxt + matchStr + afterTxt;
    //         matcher = pattern.matcher(newTxt);

    //         fileCount++;
    //     }
  
        modifyDTO.setAfter(after);
        blobService.setPost_now(modifyDTO.getAfter());

        Commits commit = CommitMapper.Instance.toModifiedCommitsEntity(postId, userId, modifyDTO);

        //5. first set postmodification first
        //before that parse the diffResult to get the before text length
        //and after text length
        PostModification postModification = getPostModificationEntity(modifyDTO.getAfter(), before, commit);

        postModificationRepository.save(postModification);

        //post line detail
        savePostLineDetail(userId, postId, postModification);

        //line detail -> after s3 connection!

    }


    public void saveNewCommitWithHistory(String token, int projectId, int commitId, CommitHistoryRevertDTO chrDTO) throws Exception
    {
        int userId = jwtUtil.getUserId(token);

        //get after string from commitId
        Commits commit = commitRepoistory.findByCommitId(commitId);
        String after = commit.getPost().replace("</p>", "</p>\n");
        after = after.replace("</h1>", "</h1>\n");
        after = after.replace("</h2>", "</h2>\n");
        after = after.replace("</h3>", "</h3>\n");
        after = after.replace("</code>", "</code>\n");
        after = after.replace("</li>", "</li>\n");
        after = after.replace("</blockquote>", "</blockquote>\n");
        after = after.replace("</hr>", "</hr>\n");
        
        String before = blobService.getPost_now();
        blobService.setPost_now(after);

        int postId = commit.getPostId();

        Commits commitRevertVersion = CommitMapper.Instance.toHistoryCommitsEntity(postId, userId, after, chrDTO);

        PostModification postModification = getPostModificationEntity(after, before, commitRevertVersion);

        postModificationRepository.save(postModification);
        
        //post line detail
        savePostLineDetail(userId, postId, postModification);

    }

    public String checkModifying( String token, int postId)
    {
        Posts post = postRespository.findByIds(postId);
        int userId = jwtUtil.getUserId(token);

        Boolean someoneTakenModifying = (post.getModifying() != 0);
        if(someoneTakenModifying)
        {
            return "failed";
        }
        else
        {
            post.setModifying(1);
            post.setModifyingUserId(userId);

            postRespository.save(post);
            
            return "success";
        }
        
    }

    public void cancelModifying( String token, int postId)
    {
        Posts post = postRespository.findByIds(postId);

        post.setModifying(0);
        post.setModifyingUserId(-1);

        postRespository.save(post);

    }

    public Boolean checkModifyingWhenReturnBlob(int postId)
    {
        Posts post = postRespository.findByIds(postId);
        Boolean someoneTakenModifying = (post.getModifying() != 0);

        return someoneTakenModifying;
    }

    public void setModifiedComplete(int postId)
    {
        Posts post = postRespository.findByIds(postId);

        post.setModifying(0);
        post.setModifyingUserId(-1);

        postRespository.save(post);

    }

    private PostModification getPostModificationEntity(String after, String before, Commits commitRevertVersion) throws Exception {
        String diffResult = DiffProvider.getDiffStr(before, after, "Diff");

        String[] lengthShowLine = diffResult.split("\n");
        String[] lengthResult = lengthShowLine[2].split(",| |@@");

        int beforePostLength =Integer.parseInt(lengthResult[3]);
        int afterPostLength = Integer.parseInt(lengthResult[5]);

        PostModification postModification = CommitPostModificationMapper.Instance.toModifiedPMEntity(diffResult, beforePostLength, afterPostLength);

        postModification.setCommits(commitRevertVersion);
        return postModification;
    }

    private void savePostLineDetail(int userId, int postId, PostModification postModification) {
        String diffResult = postModification.getDiffResult();
        String[] lengthShowLine = diffResult.split("\n");
        int realLineCount = 0;

        for(int i =3; i<lengthShowLine.length; i++)
        {

            if(lengthShowLine[i].startsWith("+<"))
            {
                String detailResult = lengthShowLine[i].replace("+","");
                PostLineDetail postDetail = PostDetailMapper.Instance.postDetailToEntity(postId, detailResult,userRepository.findByIdNumber(userId).getNickname(),realLineCount );
                commitDetailRepository.save(postDetail);
                realLineCount++;

            }

            else if(lengthShowLine[i].startsWith("-<") || lengthShowLine[i].startsWith("-new post!"))
            {
               // do nothing db have this

            }

            else
            {
                realLineCount++;
            }

        }
    }


    /**
     * @return ModifyCommitRepoistory return the commitRepoistory
     */
    public ModifyCommitRepoistory getCommitRepoistory() {
        return commitRepoistory;
    }

    /**
     * @param commitRepoistory the commitRepoistory to set
     */
    public void setCommitRepoistory(ModifyCommitRepoistory commitRepoistory) {
        this.commitRepoistory = commitRepoistory;
    }

    /**
     * @return JwtUtil return the jwtUtil
     */
    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    /**
     * @param jwtUtil the jwtUtil to set
     */
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * @return PostService return the postService
     */
    public PostService getPostService() {
        return postService;
    }

    /**
     * @param postService the postService to set
     */
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * @return BlobService return the blobService
     */
    public BlobService getBlobService() {
        return blobService;
    }

    /**
     * @param blobService the blobService to set
     */
    public void setBlobService(BlobService blobService) {
        this.blobService = blobService;
    }

    /**
     * @return ModifyPostModificationRepository return the postModificationRepository
     */
    public ModifyPostModificationRepository getPostModificationRepository() {
        return postModificationRepository;
    }

    /**
     * @param postModificationRepository the postModificationRepository to set
     */
    public void setPostModificationRepository(ModifyPostModificationRepository postModificationRepository) {
        this.postModificationRepository = postModificationRepository;
    }

    /**
     * @return S3Uploader return the s3Uploader
     */
    public S3Uploader getS3Uploader() {
        return s3Uploader;
    }

    /**
     * @param s3Uploader the s3Uploader to set
     */
    public void setS3Uploader(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    /**
     * @return PostRepository return the postRespository
     */
    public PostRepository getPostRespository() {
        return postRespository;
    }

    /**
     * @param postRespository the postRespository to set
     */
    public void setPostRespository(PostRepository postRespository) {
        this.postRespository = postRespository;
    }

    /**
     * @return BlobRepository return the blobRepository
     */
    public BlobRepository getBlobRepository() {
        return blobRepository;
    }

    /**
     * @param blobRepository the blobRepository to set
     */
    public void setBlobRepository(BlobRepository blobRepository) {
        this.blobRepository = blobRepository;
    }

    /**
     * @return MultipartFile[] return the wholeFiles
     */
    public MultipartFile[] getWholeFiles() {
        return wholeFiles;
    }

    /**
     * @param wholeFiles the wholeFiles to set
     */
    public void setWholeFiles(MultipartFile[] wholeFiles) {
        this.wholeFiles = wholeFiles;
    }

}
