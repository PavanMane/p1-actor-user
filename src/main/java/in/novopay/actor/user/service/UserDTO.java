package in.novopay.actor.user.service;

import java.util.List;

public class UserDTO {

	private String first_name;
	private String last_name;
	private String salutation;
	private String dob;
	private String email;
	private String username;
	private String password;
	private String gender;
	private String mobile;
	private List<AddressDTO> addresses;
	private List<String> roles;
	private List<String> offices;
	private List<String> document_ids;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public List<String> getOffices() {
		return offices;
	}
	public void setOffices(List<String> offices) {
		this.offices = offices;
	}
	public List<String> getDocument_ids() {
		return document_ids;
	}
	public void setDocument_ids(List<String> document_ids) {
		this.document_ids = document_ids;
	}
	
}
