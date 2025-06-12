package com.diferent.springsandbox.web;

import com.diferent.springsandbox.domain.ManageRoomService;
import com.diferent.springsandbox.domain.utils.AuthFirewall;
import com.diferent.springsandbox.model.api.request.CreateRoomRequest;
import com.diferent.springsandbox.model.api.request.UpdateRoomRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manage/room")
@AllArgsConstructor
public class ManageRoomController {

	private ManageRoomService manageRoomService;
	private AuthFirewall firewall;

	@PostMapping()
	public ResponseEntity<Void> createRoom(
		@RequestBody CreateRoomRequest request,
		@RequestHeader("Authorization") String token
	) {

		firewall.justAdminUsers(token);

		manageRoomService.createRoom(request);

		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<Void> updateRoom(
		@RequestBody UpdateRoomRequest request,
		@RequestHeader("Authorization") String token
	) {
		firewall.justAdminUsers(token);

		manageRoomService.updateRoom(request);

		return ResponseEntity.ok().build();
	}
}
