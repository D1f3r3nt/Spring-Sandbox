package com.diferent.springsandbox.model.api.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RoomResponse {

	private Long id;

	private String level;

	private String door;

	private Integer capacity;

	private LocalDateTime startDate;

	private LocalDateTime endDate;
}
