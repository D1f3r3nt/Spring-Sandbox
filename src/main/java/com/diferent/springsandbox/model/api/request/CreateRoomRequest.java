package com.diferent.springsandbox.model.api.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateRoomRequest {

	private String level;

	private String door;

	private Integer capacity;

	private LocalDateTime startDate;

	private LocalDateTime endDate;
}
