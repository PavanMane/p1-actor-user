package in.novopay.actor.user.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.novopay.actor.BaseResponse;
import in.novopay.actor.ResponseCode;
import in.novopay.actor.user.service.IUserService;
import in.novopay.actor.user.service.UserDTO;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<? extends Object> getAll() {
		try {
			List<UserDTO> userDTOs = userService.getAll();
			if (CollectionUtils.isEmpty(userDTOs)) {
				userDTOs = Collections.<UserDTO>emptyList();
			} 
			return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BaseResponse>(new BaseResponse(ResponseCode.ERROR, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<? extends Object> getUserDetailsByEmail(@PathVariable String email) {
		try {
			UserDTO userDTO = userService.getUser(email);
			if(userDTO == null) {
				return new ResponseEntity<BaseResponse>(
						new BaseResponse(ResponseCode.ERROR, "No data found for email: " + email),
						HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<BaseResponse>(new BaseResponse(ResponseCode.ERROR, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<? extends Object> createUser(@RequestBody UserDTO userDTO) {
		BaseResponse response = new BaseResponse();
		try {
			userService.save(userDTO);
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("Created User!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(ResponseCode.ERROR);
			response.setMessage(e.getMessage());
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<? extends Object> updateUser(@RequestBody UserDTO userDTO) {
		BaseResponse response = new BaseResponse();
		try {
			userService.update(userDTO);
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("Updated user details!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(ResponseCode.ERROR);
			response.setMessage(e.getMessage());
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/email/{email:.+}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<BaseResponse> delete(@PathVariable String email) {
		BaseResponse response = new BaseResponse();
		try {
			userService.delete(email);
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("Deleted user with email: " + email);
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(ResponseCode.ERROR);
			response.setMessage(e.getMessage());
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
