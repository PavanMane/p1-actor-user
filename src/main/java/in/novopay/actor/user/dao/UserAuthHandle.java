package in.novopay.actor.user.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@CompoundIndexes({
    @CompoundIndex(name = "CX_UserAuth_handleTypeAsc_authTypeAsc",
                   unique = true,
                   def = "{'handle_type' : 1, 'auth_type' : 1}")
})
public class UserAuthHandle {
	
	@Id
	private String id;
	
	@Field(value="handle_type")
	private String handleType;
	
	@Field(value="handle_value")
	@TextIndexed
	private String handleValue;
	
	@Field(value="auth_type")
	private String authType;
	
	@Field(value="auth_value")
	private String authValue;
	
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	public String getHandleValue() {
		return handleValue;
	}
	public void setHandleValue(String handleValue) {
		this.handleValue = handleValue;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthValue() {
		return authValue;
	}
	public void setAuthValue(String authValue) {
		this.authValue = authValue;
	}
	
	
}
