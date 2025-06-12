package com.diferent.springsandbox.domain;

import com.diferent.springsandbox.model.api.request.CreateRoomRequest;
import com.diferent.springsandbox.model.dto.RoomDto;
import com.diferent.springsandbox.repository.handlers.RoomHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManageRoomService {

	private RoomHandler roomHandler;

	public void createRoom(CreateRoomRequest request) {

		RoomDto roomDto = RoomDto.builder()
			.level(request.getLevel())
			.door(request.getDoor())
			.capacity(request.getCapacity())
			.endDate(request.getEndDate())
			.startDate(request.getStartDate())
			.build();

		roomHandler.save(roomDto);
	}
}
