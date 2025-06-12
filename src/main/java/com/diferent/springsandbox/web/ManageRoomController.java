package com.diferent.springsandbox.web;

import com.diferent.springsandbox.domain.ManageRoomService;
import com.diferent.springsandbox.model.api.request.CreateRoomRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manage/room")
@AllArgsConstructor
public class ManageRoomController {

	private ManageRoomService manageRoomService;

	@PostMapping()
	public ResponseEntity<Void> createRoom(@RequestBody CreateRoomRequest request) {

		manageRoomService.createRoom(request);

		return ResponseEntity.ok().build();
	}
}
