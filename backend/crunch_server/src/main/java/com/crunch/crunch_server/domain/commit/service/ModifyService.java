package com.crunch.crunch_server.domain.commit.service;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.dto.CommitHistoryRevertDTO;
import com.crunch.crunch_server.domain.commit.dto.ModifyDTO;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.entity.PostModification;
import com.crunch.crunch_server.domain.commit.mapper.CommitMapper;
import com.crunch.crunch_server.domain.commit.mapper.CommitPostModificationMapper;
import com.crunch.crunch_server.domain.commit.repository.ModifyCommitRepoistory;
import com.crunch.crunch_server.domain.commit.repository.ModifyPostModificationRepository;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        modifyDTO.setAfter(modifyDTO.getAfter().replace("</p>", "</p>\n"));
        blobService.setPost_now(modifyDTO.getAfter());

        Commits commit = CommitMapper.Instance.toModifiedCommitsEntity(postId, userId, modifyDTO);

        //5. first set postmodification first
        //before that parse the diffResult to get the before text length
        //and after text length
        PostModification postModification = getPostModificationEntity(modifyDTO.getAfter(), before, commit);

        postModificationRepository.save(postModification);
        
        //line detail -> after s3 connection!

    }

    public void saveNewCommitWithHistory(String token, int projectId, int commitId, CommitHistoryRevertDTO chrDTO) throws Exception
    {
        int userId = jwtUtil.getUserId(token);

        //get after string from commitId
        Commits commit = commitRepoistory.findByCommitId(commitId);
        String after = commit.getPost().replace("</p>", "</p>\n");
        String before = blobService.getPost_now();
        blobService.setPost_now(after);

        int postId = commit.getPostId();

        Commits commitRevertVersion = CommitMapper.Instance.toHistoryCommitsEntity(postId, userId, after, chrDTO);

        PostModification postModification = getPostModificationEntity(after, before, commitRevertVersion);

        postModificationRepository.save(postModification);


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


}
