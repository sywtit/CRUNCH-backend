package com.crunch.crunch_server.domain.project.repository;

import java.util.List;

import com.crunch.crunch_server.domain.project.entity.Tag;
import com.crunch.crunch_server.domain.project.entity.TagIdentity;
import com.crunch.crunch_server.domain.user.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, TagIdentity> {

	List<Tag> findByText(String tagText);

	// List<Tag> findByTextAndState(String tagText, String string);

}
