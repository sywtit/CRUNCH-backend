package com.crunch.crunch_server.domain.crew.repository;

import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
// import com.crunch.crunch_server.domain.crew.entity.BuyerCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrewEntity;
import com.crunch.crunch_server.domain.crew.entity.TmpBuyerCrew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BuyerCrewRepository extends JpaRepository<TmpBuyerCrew, Integer> {

    // void save(BuyerCrewEntity buyerCrewEntity);

    // BuyerCrew save(BuyerCrew buyerCrew);

}
