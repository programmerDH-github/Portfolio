package com.bside.BSIDE.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.user.domain.UserDto;
import com.bside.BSIDE.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @UserController
 * @작성자 DongHun
 * @일자 2023.05.10.
 **/

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }    
    
    /* 회원 조회 */    
    @GetMapping("/select/{email}")
    @Operation(summary = "회원 조회", description = "String eml")
    public UserDto getUserByEmail(@PathVariable String email) {
    	return userService.getUserByEmail(email);
    }
    
    /* 회원 탈퇴 */    
    @DeleteMapping("/delete/{email}")
    @Operation(summary = "회원 탈퇴", description = "String eml")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        try {
            int deletedRows = userService.deleteUser(email);
            if (deletedRows == 0) {
                return ResponseEntity.ok("해당 이메일을 가진 사용자가 존재하지 않습니다.");
            } else {
                return ResponseEntity.ok("사용자 삭제 성공");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    /* 회원 수정 */
    @PutMapping("/update/{email}")
    @Operation(summary = "회원 정보 수정", description = "String eml")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        userDto.setEml(email);
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }
    
    /* 로그인 */
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
    	UserDto user = userService.getUserByEmailPw(email, password);
    	
    	if(user != null) {
    		return ResponseEntity.ok().body(user);
    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    /* 이메일 주소의 유효성 검사 */
	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(regex);
	}
}
