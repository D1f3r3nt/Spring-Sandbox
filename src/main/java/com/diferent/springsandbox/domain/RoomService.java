package com.diferent.springsandbox.domain;

import com.diferent.springsandbox.model.api.response.RoomResponse;
import com.diferent.springsandbox.model.dto.RoomDto;
import com.diferent.springsandbox.repository.handlers.RoomHandler;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

	private RoomHandler roomHandler;

	public List<RoomResponse> getListOfRooms() {
		List<RoomDto> rooms = roomHandler.obtainRooms();

		List<RoomDto> sortedRooms = rooms.stream()
			.sorted((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()))
			.toList();

		return toListRoomResponse(sortedRooms);
	}

	private List<RoomResponse> toListRoomResponse(List<RoomDto> rooms) {
		return rooms.stream().map(roomDto -> RoomResponse.builder()
			.id(roomDto.getId())
			.level(roomDto.getLevel())
			.door(roomDto.getDoor())
			.capacity(roomDto.getCapacity())
			.startDate(roomDto.getStartDate())
			.endDate(roomDto.getEndDate())
			.build()).toList();
	}

}
