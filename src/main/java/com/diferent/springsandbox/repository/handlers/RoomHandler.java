package com.diferent.springsandbox.repository.handlers;

import com.diferent.springsandbox.domain.errors.ServiceException;
import com.diferent.springsandbox.model.dto.RoomDto;
import com.diferent.springsandbox.model.entity.RoomEntity;
import com.diferent.springsandbox.repository.RoomRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomHandler {

	private RoomRepository roomRepository;

	public List<RoomDto> obtainRooms() {
		try {

			List<RoomEntity> rooms = roomRepository.findAll();

			return toListRoomDto(rooms);

		} catch (Exception e) {
			throw new ServiceException.Builder("ERR-010")
				.withMessage("Something went wrong")
				.withHttpStatus(HttpStatus.SERVICE_UNAVAILABLE)
				.build();
		}
	}

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

	public void delete(Long id) {

		try {
			roomRepository.deleteById(id);
		} catch (Exception e) {
			throw new ServiceException.Builder("ERR-010")
				.withMessage("Id not found")
				.withHttpStatus(HttpStatus.BAD_REQUEST)
				.build();
		}
	}

	private List<RoomDto> toListRoomDto(List<RoomEntity> entities) {
		return entities.stream().map(roomEntity -> RoomDto.builder()
			.id(roomEntity.getId())
			.door(roomEntity.getDoor())
			.level(roomEntity.getLevel())
			.capacity(roomEntity.getCapacity())
			.startDate(roomEntity.getStartDate())
			.endDate(roomEntity.getEndDate())
			.build()).toList();
	}
}
