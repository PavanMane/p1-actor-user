package in.novopay.actor.user.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	
	@Id
	private String id;
	
	@TextIndexed
	private String firstName;
	
	@TextIndexed
	private String lastName;
	
	private String salutation;
	private String gender;
	
	@TextIndexed
	private String email;
	private Date dob;
	private String locale;
	private String status;
	private List<UserAuthHandle> userAuthHandles;
	private List<Address> addresses;
	private List<String> roles;
	private List<String> offices;
	private List<String> docIds;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<UserAuthHandle> getUserAuthHandles() {
		return userAuthHandles;
	}
	public void setUserAuthHandles(List<UserAuthHandle> userAuthHandles) {
		this.userAuthHandles = userAuthHandles;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<String> getDocIds() {
		return docIds;
	}
	public void setDocIds(List<String> docIds) {
		this.docIds = docIds;
	}
	
}
