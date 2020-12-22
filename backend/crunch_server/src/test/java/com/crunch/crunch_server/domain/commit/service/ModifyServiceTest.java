package com.crunch.crunch_server.domain.commit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.crunch.crunch_server.diff.DiffProvider;
import com.crunch.crunch_server.domain.commit.entity.Commits;
import com.crunch.crunch_server.domain.commit.entity.PostModification;
import com.crunch.crunch_server.domain.commit.repository.BlobRepository;
import com.crunch.crunch_server.domain.commit.repository.CommitDetailRepository;
import com.crunch.crunch_server.domain.commit.repository.ModifyCommitRepoistory;
import com.crunch.crunch_server.domain.commit.repository.ModifyPostModificationRepository;
import com.crunch.crunch_server.domain.project.repository.PostRepository;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.util.JwtUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ModifyServiceTest {

    @InjectMocks
      private ModifyService modifyService;
    
      @Mock
      private ModifyCommitRepoistory commitRepoistory;
      @Mock
      private JwtUtil jwtUtil;
      @Mock
      private PostService postService;
      @Mock
      private BlobService blobService;
      @Mock
      private ModifyPostModificationRepository postModificationRepository;

      @Mock
      private PostRepository postRespository;
      @Mock
      private BlobRepository blobRepository;
      @Mock
      private UserRepository userRepository;
      @Mock
      private CommitDetailRepository commitDetailRepository;
 

      @Test
      public void getDiffResult() throws Exception {
          //setup fixture
          String before = "<p>hello everyone</p>\n";
          String after = "<p>hello everyone</p>\n<p>today is a good day!</p>\n";
          String timeNow = "2020-12-22 12:33";
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          LocalDateTime currentTime = LocalDateTime.parse(timeNow, formatter);
          Commits newCommit  = new Commits(1,1,1,"let's start",currentTime, "",after);
          
          //verify outcome
          PostModification newPostModification = modifyService.getPostModificationEntity(after, before, newCommit);
          String expectedDiffResult = DiffProvider.getDiffStr(before, after, "Diff");
          assertTrue(newPostModification.getAfterPostLength() == 2);
          assertTrue(newPostModification.getBeforePostLength() == 1);
          assertEquals(newPostModification.getDiffResult(), expectedDiffResult);

          //cleaning up fixture
          deleteObject(newCommit);

      }

      private void deleteObject(Object ob) {
        ob = null;
    }

    
}
