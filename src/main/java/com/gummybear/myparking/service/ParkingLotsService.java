package com.gummybear.myparking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gummybear.myparking.domain.favorites.Favorites;
import com.gummybear.myparking.domain.favorites.FavoritesRepository;
import com.gummybear.myparking.domain.parking.ParkingLots;
import com.gummybear.myparking.domain.parking.ParkingLotsRepository;
import com.gummybear.myparking.web.dto.FavoritesCreateRequestDto;
import com.gummybear.myparking.web.dto.FavoritesResponseDto;
import com.gummybear.myparking.web.dto.FavoritesUpdateRequestDto;
import com.gummybear.myparking.web.dto.ParkingLotsListResponseDto;
import com.gummybear.myparking.web.dto.ParkingLotsRequestDto;
import com.gummybear.myparking.web.dto.ParkingLotsResponseDto;
import com.gummybear.myparking.web.dto.ParkingLotsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ParkingLotsService {
	
	private final ParkingLotsRepository parkingLotsRepository;
	private final FavoritesRepository favoritesRepository;
	
	// 주차장 데이터 등록
	@Transactional
	public Long save(ParkingLotsRequestDto requestDto) {
		return parkingLotsRepository.save(requestDto.toEntity()).getId();
	}
	
	// 주차장별 상세 데이터 조회
	public ParkingLotsResponseDto findById(Long id) {
		ParkingLots entity = parkingLotsRepository.findById(id).orElseThrow(() -> 
			new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
		
		return new ParkingLotsResponseDto(entity);
	}
	
	// 주차장 데이터 수정
	@Transactional
	public Long update(Long id, ParkingLotsUpdateRequestDto requestDto) {
		ParkingLots parkingLots = parkingLotsRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
		
		// dirty checking
		parkingLots.update(requestDto);
		
		return id;
	}
	
	// 주차장 데이터 삭제
	@Transactional
	public void delete(Long id) {
		ParkingLots parkingLots = parkingLotsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
		parkingLotsRepository.delete(parkingLots);
	}
	
	// 인덱스 페이지 > 주차장 목록 조회
	@Transactional(readOnly = true)
	public List<ParkingLotsListResponseDto> findParkingLotList() {
		return parkingLotsRepository.findParkingLotList().stream().map(ParkingLotsListResponseDto::new).collect(Collectors.toList());
	}

	// 2.사용자가 즐겨찾기 한 주차장 조회
	public ParkingLotsResponseDto selectFavorites(Long id) {
		ParkingLots entity = parkingLotsRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
	
		return new ParkingLotsResponseDto(entity);
	}
	// 내 위치 주변 주차장 정보 조회
	public ParkingLotsResponseDto findByMyLocation(Long id) {
		ParkingLots entity = parkingLotsRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
		
		return new ParkingLotsResponseDto(entity);
	}
	
	// 조건으로 주차장 검색 시,도/구,군,시/동,읍
	//parkingLotsService.findByOption(location1,location2,location3);
	//institudeName Where 절
	public ParkingLotsResponseDto findByOption(String location1, String location2, String location3) {
		String addroad = location1 +" " + location2 + " " +location3 + "%";
		ParkingLots entity = parkingLotsRepository.findByRoadNameAddressLike(addroad).orElseThrow(() -> 
		new IllegalArgumentException("해당 주차장 정보가 없습니다. addroad=" + addroad));
		
		return new ParkingLotsResponseDto(entity);
	}
	
	// 조건으로 주차장 검색(영업시간)
	@Transactional(readOnly = true)
	public List<ParkingLotsListResponseDto> findByOpeningHour(String openingHour) {
		return parkingLotsRepository.findByOpeningHour(openingHour).stream().map(ParkingLotsListResponseDto::new).collect(Collectors.toList());
	}

	// 조건으로 주차장 검색(무료/유료)
	@Transactional(readOnly = true)
	public List<ParkingLotsListResponseDto> findByParkingFeeInformation(String parkingFeeInformation) {
		return parkingLotsRepository.findByParkingFeeInformation(parkingFeeInformation).stream().map(ParkingLotsListResponseDto::new).collect(Collectors.toList());
	}

	// 사용자 즐겨찾기 생성
	@Transactional
	public Long createFavorites(FavoritesCreateRequestDto requestDto) {
		return favoritesRepository.save(requestDto.toEntity()).getId();
	}
	
	// 사용자 즐겨찾기 목록 조회
	@Transactional(readOnly = true)
	public List<FavoritesResponseDto> findFavorites(Long userId) {
		return favoritesRepository.findAllByUserId(userId).stream().map(FavoritesResponseDto::new).collect(Collectors.toList());
	}
	
	// 사용자 즐겨찾기 수정
	@Transactional
	public Long updateFavorites(Long id, FavoritesUpdateRequestDto requestDto) {
		Favorites favorites = favoritesRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("해당 즐겨찾기가 없습니다. id=" + id));
		
		favorites.update(requestDto);
		
		return id;
	}

	// 사용자 즐겨찾기 삭제
	public void deleteFavorites(Long id) {
		Favorites favorites = favoritesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 즐겨찾기가 없습니다. id=" + id));
		favoritesRepository.delete(favorites);
	}

	
	// 4.평가 데이터 별 주차장 조회
	/*
	public ParkingLotsResponseDto selectByScore(int page) {
		ParkingLots entity = parkingLotsRepository.findById(id).orElseThrow(() -> 
		new IllegalArgumentException("해당 주차장 정보가 없습니다. id=" + id));
	
		return new ParkingLotsResponseDto(entity);
	}
	*/

}
