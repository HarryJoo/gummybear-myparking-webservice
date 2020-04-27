package com.gummybear.myparking.domain.parking;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gummybear.myparking.web.dto.ParkingLotsListResponseDto;

public interface ParkingLotsRepository extends JpaRepository<ParkingLots, Long> {
	@Query("SELECT p FROM ParkingLots p ORDER BY p.id ASC")
	List<ParkingLots> findParkingLotList();

	Optional<ParkingLots> findByRoadNameAddressLike(String addroad);
	
	@Query("SELECT p FROM ParkingLots p WHERE weekday_opening_hour = :openingHour")
	List<ParkingLots> findByOpeningHour(@Param("openingHour") String openingHour);

	@Query("SELECT p FROM ParkingLots p WHERE parking_fee_information = :parkingFeeInformation")
	List<ParkingLots> findByParkingFeeInformation(@Param("parkingFeeInformation") String parkingFeeInformation);
}
