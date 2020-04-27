package com.gummybear.myparking.domain.favorites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
	List<Favorites> findAllByUserId(Long userId);
}
