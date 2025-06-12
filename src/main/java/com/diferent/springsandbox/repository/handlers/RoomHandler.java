package com.diferent.springsandbox.repository.handlers;

import com.diferent.springsandbox.domain.errors.ServiceException;
import com.diferent.springsandbox.model.dto.RoomDto;
import com.diferent.springsandbox.model.entity.RoomEntity;
import com.diferent.springsandbox.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomHandler {

	private RoomRepository roomRepository;

	public void save(RoomDto roomDto) {

		RoomEntity roomEntity = RoomEntity.builder()
			.door(roomDto.getDoor())
			.level(roomDto.getLevel())
			.capacity(roomDto.getCapacity())
			.endDate(roomDto.getEndDate())
			.startDate(roomDto.getStartDate())
			.build();

		try {
			roomRepository.save(roomEntity);
		} catch (Exception e) {
			throw new ServiceException.Builder("ERR-010")
				.withMessage("Duplicated ID")
				.withHttpStatus(HttpStatus.BAD_REQUEST)
				.build();
		}
	}

	public void update(RoomDto roomDto) {

		try {
			roomRepository.update(
				roomDto.getId(),
				roomDto.getLevel(),
				roomDto.getDoor(),
				roomDto.getCapacity(),
				roomDto.getStartDate(),
				roomDto.getEndDate()
			);
		} catch (Exception e) {
			throw new ServiceException.Builder("ERR-010")
				.withMessage("Duplicated ID")
				.withHttpStatus(HttpStatus.BAD_REQUEST)
				.build();
		}
	}
}
