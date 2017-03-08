package in.novopay.actor.user.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Address {

	@Id
	private String id;
	
	private String type;
	
	@Field(value="address_ln_1")
	private String addreessLn1;
	
	@Field(value="address_ln_2")
	private String addreessLn2;
	
	@Field(value="address_ln_3")
	private String addreessLn3;
	
	@Field(value="country_code")
	private String countryCode;
	
	private String country;
	
	@Field(value="state_code")
	private String stateCode;
	
	private String state;
	
	@Field(value="district_code")
	private String districtCode;
	
	private String district;
	
	@Field(value="sub_district_code")
	private String subDistrictCode;
	
	@Field(value="sub_district")
	private String subDistrict;
	
	@Field(value="vtc_code")
	private String vtcCode;
	
	private String vtc;
	
	private String pincode;
	private String locality;
	private String landmark;
	
	@Field(value="address_geocoded_lat_long")
	private String addressGeoCodedLatLong;
	
	@Field(value="created_by")
	private String createdBy;
	
	@Field(value="created_on")
	private Date createdOn;
	
	@Field(value="updated_by")
	private String updatedBy;
	
	@Field(value="updated_on")
	private Date updatedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddreessLn1() {
		return addreessLn1;
	}

	public void setAddreessLn1(String addreessLn1) {
		this.addreessLn1 = addreessLn1;
	}

	public String getAddreessLn2() {
		return addreessLn2;
	}

	public void setAddreessLn2(String addreessLn2) {
		this.addreessLn2 = addreessLn2;
	}

	public String getAddreessLn3() {
		return addreessLn3;
	}

	public void setAddreessLn3(String addreessLn3) {
		this.addreessLn3 = addreessLn3;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getVtcCode() {
		return vtcCode;
	}

	public void setVtcCode(String vtcCode) {
		this.vtcCode = vtcCode;
	}

	public String getVtc() {
		return vtc;
	}

	public void setVtc(String vtc) {
		this.vtc = vtc;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getAddressGeoCodedLatLong() {
		return addressGeoCodedLatLong;
	}

	public void setAddressGeoCodedLatLong(String addressGeoCodedLatLong) {
		this.addressGeoCodedLatLong = addressGeoCodedLatLong;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
