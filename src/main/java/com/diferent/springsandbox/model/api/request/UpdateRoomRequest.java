package com.diferent.springsandbox.model.api.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder(toBuilder = true)
public class UpdateRoomRequest {

	@NonNull
	private Long id;

	@Nullable
	private String level;

	@Nullable
	private String door;

	@Nullable
	private Integer capacity;

	@Nullable
	private LocalDateTime startDate;

	@Nullable
	private LocalDateTime endDate;
}
