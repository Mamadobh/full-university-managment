package com.global.university.track;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepo  extends JpaRepository<Track,Integer>, BaseRepo<Track,Integer> {
}
