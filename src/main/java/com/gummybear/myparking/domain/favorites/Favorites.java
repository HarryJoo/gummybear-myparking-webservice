package com.gummybear.myparking.domain.favorites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gummybear.myparking.web.dto.FavoritesUpdateRequestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DB 테이블 전용인 Entity Class
@Getter
@NoArgsConstructor
@Entity
public class Favorites {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Long userId;
	
	//@ManyToOne
	//@JoinColumn(name="user_id")
	//private User user;
	
	@Builder
	public Favorites(String name, Long userId) {
		this.name = name;
		this.userId = userId;

	}

	public void update(FavoritesUpdateRequestDto dto) {
		this.name = dto.getName();
	}
	
}
