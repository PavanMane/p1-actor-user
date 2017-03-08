package in.novopay.actor.user.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import in.novopay.actor.BaseSpringTest;
import in.novopay.actor.user.dao.Address;
import in.novopay.actor.user.dao.IUserDAO;
import in.novopay.actor.user.dao.User;
import in.novopay.actor.user.dao.UserAuthHandle;

public class UserDAOImplTest extends BaseSpringTest {

	@Autowired
	private IUserDAO userDAO;
	
	@Before
	public void init() {
		Assert.notNull(userDAO);
	}
	
	@Test
	public void testAll() {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com";
		try {
			User user = userDAO.save(getSampleData(firstName, email));
			List<User> all = userDAO.getAll();
			Assert.isTrue(!CollectionUtils.isEmpty(all));
			String firstNameUpdt = firstName + "_updated";
			user.setFirstName(firstNameUpdt);
			userDAO.update(user);
			user = userDAO.getUser(email);
			Assert.isTrue(firstNameUpdt.equals(user.getFirstName()));
		} finally {
			userDAO.delete(email);
			Assert.isNull(userDAO.getUser(email));
		}
	}
	
	public static User getSampleData(String firstName, String email) {
		User user = new User();
		user.setDob(new Date());
		user.setEmail(email);
		user.setFirstName("Pavan");
		user.setGender("Male");
		user.setLastName("Mane");
		user.setLocale("en_US");
		user.setSalutation("Mr.");
		user.setStatus("ACTIVE");
		
		Address address = new Address();
		address.setAddreessLn1("blah1");
		address.setAddreessLn2("blah2");
		address.setAddreessLn3("blah3");
		address.setAddressGeoCodedLatLong("180.80098::160.897987");
		address.setCountry("INDIA");
		address.setCountryCode("15");
		address.setState("KARNATAKA");
		address.setStateCode("12");
		address.setCreatedBy("P1");
		address.setCreatedOn(new Date());
		address.setDistrict("BANGALORE");
		address.setDistrictCode("25");
		address.setLandmark("Spring fields");
		address.setLocality("Sarjapur rod");
		address.setPincode("560103");
		address.setSubDistrict("BLR_SOUTH");
		address.setSubDistrictCode("1115");
		address.setType("Permanent");
		address.setUpdatedBy("p1");
		address.setUpdatedOn(new Date());
		address.setVtc("Bellandur");
		address.setVtcCode("BLLRD");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		user.setAddresses(addresses);
		
		UserAuthHandle uah = new UserAuthHandle();
		uah.setHandleType("EMAIL");
		uah.setHandleValue(email);
		uah.setAuthType("PASSWORD");
		uah.setAuthValue("123edo909325asdfk");
		List<UserAuthHandle> uahs = new ArrayList<UserAuthHandle>();
		uahs.add(uah);
		user.setUserAuthHandles(uahs);
		
		List<String> roles = new ArrayList<String>(2);
		roles.add("Regional_Officer");
		roles.add("Relationship_Manager");
		user.setRoles(roles);
		
		List<String> offices = new ArrayList<String>(2);
		offices.add("Bellandur");
		offices.add("Kaikondrahalli");
		user.setOffices(offices);
		
		List<String> docIds = new ArrayList<String>(2);
		docIds.add("pan_card_urn");
		docIds.add("passport_urn");
		user.setDocIds(docIds);
		
		return user;
		
	}
}
