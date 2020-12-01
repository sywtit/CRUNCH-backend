package com.crunch.crunch_server.domain.crew.repository;

import java.time.LocalDate;
import java.util.List;

import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
// import com.crunch.crunch_server.domain.crew.entity.BuyerCrewIdentity;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrewEntity;
import com.crunch.crunch_server.domain.crew.entity.TmpBuyerCrew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BuyerCrewRepository extends JpaRepository<BuyerCrew, Integer> {

	static List<BuyerCrew> findByPurchaseDateBetween(LocalDate start, LocalDate end) {
		return null;
	}

	static List<BuyerCrew> findByBuyerCrewIdentityPostindexIdAndBuyerCrewIdentityProjectId(int id, Integer projectId) {
		return null;
	}

    // void save(BuyerCrewEntity buyerCrewEntity);

    // BuyerCrew save(BuyerCrew buyerCrew);

}
