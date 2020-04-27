package com.gummybear.myparking.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gummybear.myparking.service.ParkingLotsService;
import com.gummybear.myparking.web.dto.FavoritesCreateRequestDto;
import com.gummybear.myparking.web.dto.FavoritesResponseDto;
import com.gummybear.myparking.web.dto.FavoritesUpdateRequestDto;
import com.gummybear.myparking.web.dto.ParkingLotsListResponseDto;
import com.gummybear.myparking.web.dto.ParkingLotsRequestDto;
import com.gummybear.myparking.web.dto.ParkingLotsResponseDto;
import com.gummybear.myparking.web.dto.ParkingLotsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ParkingLotsAPIController {
	
	private final ParkingLotsService parkingLotsService;
	
	// 1.기본 CRUD
	// 주차장 데이터 등록
	@PostMapping("/api/v1/parking-lots")
	public Long save(@RequestBody ParkingLotsRequestDto requestDto) {
		return parkingLotsService.save(requestDto);
	}
	
	// 주차장별 상세 데이터 조회
	@GetMapping("/api/v1/parking-lots/{id}")
	public ParkingLotsResponseDto findById(@PathVariable Long id) {
		return parkingLotsService.findById(id);
	}	
	
	// 주차장 데이터 수정
	@PutMapping("/api/v1/parking-lots/{id}")
	public Long update(@PathVariable Long id, @RequestBody ParkingLotsUpdateRequestDto requestDto) {
		return parkingLotsService.update(id, requestDto);
	}

	// 주차장 데이터 삭제
	@DeleteMapping("/api/v1/parking-lots/{id}")
	public Long delete(@PathVariable Long id) {
		parkingLotsService.delete(id);
		return id;	
	}	
	
	// 2.주차장 API
	// 내 위치 주변 주차장 정보 조회 (미완성)
	@GetMapping("/api/v1/parking-lots/user/{location}")
	public ParkingLotsResponseDto findByMyLocation(@PathVariable Long id) {
		return parkingLotsService.findByMyLocation(id);
	}
	
	// 사용자가 즐겨찾기 한 주차장 조회
	//@GetMapping("/api/v1/parking-lots/favorites/{id}")
	//public ParkingLotsResponseDto selectFavorites(@PathVariable Long id) {
	//	return parkingLotsService.selectFavorites(id);
	//}	
	
	// 조건으로 주차장 검색 시,도/구,군,시/동,읍
	@GetMapping("/api/v1/parking-lots/search/{location1}/{location2}/{location3}")
	public ParkingLotsResponseDto findByOption(@PathVariable String location1,@PathVariable String location2,@PathVariable String location3) {
		return parkingLotsService.findByOption(location1,location2,location3);
	}
	
	// 조건으로 주차장 검색(영업시간)
	@GetMapping("/api/v1/parking-lots/opening-hour/{openingHour}")
	public List<ParkingLotsListResponseDto> findByOpeningHour(@PathVariable String openingHour) {
		return parkingLotsService.findByOpeningHour(openingHour);
	}
	
	// 조건으로 주차장 검색(무료/유료)
	@GetMapping("/api/v1/parking-lots/parking-fee/{parkingFeeInformation}")
	public List<ParkingLotsListResponseDto> findByParkingFeeInformation(@PathVariable String parkingFeeInformation) {
		return parkingLotsService.findByParkingFeeInformation(parkingFeeInformation);
	}
	
	// 평가 데이터 별 주차장 조회
	/*
	@GetMapping("/api/v1/parking-lots/score")
	public ParkingLotsResponseDto selectByScore(@RequestParam int page) {
		return parkingLotsService.selectByScore(page);
	}
	*/
	
	// 3.사용자의 주차장 즐겨찾기 API
	// 사용자 즐겨찾기 생성
	@PostMapping("/api/v1/parking-lots/favorites")
	public Long createFavorites(@RequestBody FavoritesCreateRequestDto requestDto) {
		return parkingLotsService.createFavorites(requestDto);
	}	
	
	// 사용자 즐겨찾기 목록 조회
	@GetMapping("/api/v1/parking-lots/favorites/{userId}")
	public List<FavoritesResponseDto> findFavorites(@PathVariable Long userId) {
		return parkingLotsService.findFavorites(userId);
	}
	
	// 사용자 즐겨찾기 수정
	@PutMapping("/api/v1/parking-lots/favorites/{id}")
	public Long createFavorites(@PathVariable Long id, @RequestBody FavoritesUpdateRequestDto requestDto) {
		return parkingLotsService.updateFavorites(id, requestDto);
	}	
	
	// 즐겨찾기 삭제
	@DeleteMapping("/api/v1/parking-lots/favorites/{id}")
	public Long deleteFavorites(@PathVariable Long id) {
		parkingLotsService.deleteFavorites(id);
		return id;
	}	
	
}
