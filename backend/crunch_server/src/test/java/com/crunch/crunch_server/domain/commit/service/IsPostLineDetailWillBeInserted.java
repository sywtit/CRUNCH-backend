package com.crunch.crunch_server.domain.commit.service;

import com.crunch.crunch_server.domain.commit.entity.PostLineDetail;

import org.mockito.ArgumentMatcher;

public class IsPostLineDetailWillBeInserted implements ArgumentMatcher<PostLineDetail>{

    @Override
    public boolean matches(PostLineDetail postLineDetail){
        return equals(postLineDetail.getLineNum(), 0)
        && equals(postLineDetail.getText(), "<p>hi everyone</p>")
        && equals(postLineDetail.getWriterName(), "star");
    }

    private boolean equals(Object actual, Object expected){
        return expected.equals(actual);
    }

}
