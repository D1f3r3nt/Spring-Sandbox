package com.diferent.springsandbox.web;

import com.diferent.springsandbox.domain.RoomService;
import com.diferent.springsandbox.domain.utils.AuthFirewall;
import com.diferent.springsandbox.model.api.response.RoomResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room")
@AllArgsConstructor
public class RoomController {

	private AuthFirewall firewall;
	private RoomService roomService;

	@GetMapping("/list")
	public ResponseEntity<List<RoomResponse>> getList(
		@RequestHeader("Authorization") String token
	) {

		firewall.checkToken(token);

		List<RoomResponse> rooms = roomService.getListOfRooms();

		return ResponseEntity.ok(rooms);
	}
}
