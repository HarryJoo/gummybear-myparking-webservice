package com.gummybear.myparking.web.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gummybear.myparking.domain.favorites.Favorites;
import com.gummybear.myparking.domain.user.User;

import lombok.Getter;

// View를 위한 DTO Class (Reqeust/Response 용)
@Getter
public class FavoritesResponseDto {
	private Long id;
	private Long userId;
	private String name;
	
	//@ManyToOne
	//@JoinColumn(name="user_id")
	//private User user;

	public FavoritesResponseDto(Favorites favorites) {
		this.id = favorites.getId();
		this.name = favorites.getName();
		this.userId = favorites.getUserId();
	}
	
}
