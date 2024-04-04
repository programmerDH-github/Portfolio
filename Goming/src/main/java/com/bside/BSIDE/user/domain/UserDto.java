package com.bside.BSIDE.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값 출력x
public class UserDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String usrNo;

	@Schema(description = "사용자 이메일", example = "programmer_h@naver.com")
	@Email
	private String eml;

	@Schema(description = "사용자 이름", example = "Lee")
	private String usrNm;

	@Schema(description = "사용자 비밀번호", example = "1234")
	private String password;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String snsClsCd;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String snsToken;
	
	@Schema(description = "이메일 수신 동의(Y/N)", example = "Y")
	private String agreement;

	@Schema(description = "사용자 성별(F/M)", example = "M")
	private String gndrClsCd;

	@Schema(description = "사용자 생성일자", example = "1993-01-01")
	private String brdt;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String joinDtm;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String lastLgnDtm;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String updateDtm;
		
	public String getEmail() {
        return eml;
    }

    public void setEmail(String eml) {
        this.eml = eml;
    }
}
