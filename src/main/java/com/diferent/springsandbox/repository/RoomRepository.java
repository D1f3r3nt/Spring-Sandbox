package com.diferent.springsandbox.repository;

import com.diferent.springsandbox.model.entity.RoomEntity;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

	@Modifying
	@Transactional
	@Query("""
        UPDATE RoomEntity r SET
            r.level = COALESCE(:level, r.level),
            r.door = COALESCE(:door, r.door),
            r.capacity = COALESCE(:capacity, r.capacity),
            r.startDate = COALESCE(:startDate, r.startDate),
            r.endDate = COALESCE(:endDate, r.endDate)
        WHERE r.id = :id
    """)
	public void update(
		@Param("id") Long id,
		@Param("level") String level,
		@Param("door") String door,
		@Param("capacity") Integer capacity,
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate
	);
}
