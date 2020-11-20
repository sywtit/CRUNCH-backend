package com.crunch.crunch_server.domain.crew.repository;

import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
// import com.crunch.crunch_server.domain.crew.entity.BuyerCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrewEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BuyerCrewRepository extends JpaRepository<BuyerCrew, Integer> {

	void save(BuyerCrewEntity buyerCrewEntity);

    // BuyerCrew save(BuyerCrew buyerCrew);

}
