package com.gummybear.myparking.web.dto;

import com.gummybear.myparking.domain.favorites.Favorites;

import lombok.Getter;
import lombok.NoArgsConstructor;

// View를 위한 DTO Class (Reqeust/Response 용)
@Getter
@NoArgsConstructor
public class FavoritesUpdateRequestDto {
	private Long id;
	private String name;
	private Long userId;
	
	public Favorites toEntity() {
		return Favorites.builder().name(name).userId(userId).build();
	}
	
}
