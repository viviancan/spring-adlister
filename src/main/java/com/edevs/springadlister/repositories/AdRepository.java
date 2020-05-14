package com.edevs.springadlister.repositories;

import com.edevs.springadlister.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByTitle(String title);

    @Query("from Ad a where a.id like ?1")
    Ad getAdById(long id);
}
