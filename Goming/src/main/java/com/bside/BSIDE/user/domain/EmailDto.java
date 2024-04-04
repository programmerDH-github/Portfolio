package com.bside.BSIDE.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값 출력x
public class EmailDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String usrNo;

	@Schema(description = "사용자 이메일", example = "programmer_h@naver.com")
	@Email
	private String email;

	@Schema(description = "월간 고밍 받는 이메일", example = "programmer_h@naver.com")
	private String sendEmail;

	@Schema(description = "월간 고밍 날짜", example = "2022-07")
	private String date;

}
