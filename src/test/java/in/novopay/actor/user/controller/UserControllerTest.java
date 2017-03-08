package in.novopay.actor.user.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.novopay.actor.BaseSpringTest;
import in.novopay.actor.user.dao.Address;
import in.novopay.actor.user.dao.IUserDAO;
import in.novopay.actor.user.dao.User;
import in.novopay.actor.user.dao.UserAuthHandle;
import in.novopay.actor.user.dao.impl.UserDAOImplTest;
import in.novopay.actor.user.service.AddressDTO;
import in.novopay.actor.user.service.UserDTO;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseSpringTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IUserDAO userDAO;
	
	@Before
	public void init() {
		Assert.notNull(userDAO);
//		Query query = new Query();
//		query.addCriteria(Criteria.where("loginName").regex(".*"));
//		mongoTemplate.remove(query, User.class);
	}

	@Test
	public void testGet() throws Exception {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com.com";
		try {
			userDAO.save(UserDAOImplTest.getSampleData(firstName, email));

			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.get("/v1/user/email/" + email).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);
			ObjectMapper om = new ObjectMapper();
			UserDTO userDTO = om.readValue(content, UserDTO.class);
			Assert.notNull(userDTO);
			Assert.isTrue(email.equals(userDTO.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.isTrue(false);
		} finally {
			userDAO.delete(email);
		}
	}
	
	@Test
	public void testGetAll() throws Exception {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com.com";
		boolean exists = false;
		try {
			userDAO.save(UserDAOImplTest.getSampleData(firstName, email));

			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.get("/v1/user/all").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);
			ObjectMapper om = new ObjectMapper();
			List<UserDTO> userDTOs = om.readValue(result.getResponse().getContentAsByteArray(),
					new TypeReference<List<UserDTO>>() {
					});
			Assert.isTrue(!CollectionUtils.isEmpty(userDTOs));
			for (UserDTO userDTO : userDTOs) {
				exists = email.equals(userDTO.getEmail());
				if(exists) 
					break;
			}
			Assert.isTrue(exists);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.isTrue(false);
		} finally {
			userDAO.delete(email);
		}
	}

	@Test
	public void testSave() throws Exception {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com";
		try {
			UserDTO userDTO = copyToDTO(UserDAOImplTest.getSampleData(firstName, email));
			ObjectMapper om = new ObjectMapper();
			String userJSON = om.writeValueAsString(userDTO);
			System.out.println("Request ---> " + userJSON);

			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.post("/v1/user").contentType(MediaType.APPLICATION_JSON).content(userJSON))
					.andExpect(status().isCreated()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);

			User user = userDAO.getUser(email);
			Assert.isTrue(email.equals(user.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.isTrue(false);
		} finally {
			userDAO.delete(email);
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com";
		try {
			User user = UserDAOImplTest.getSampleData(firstName, email);
			userDAO.save(user);
			
			UserDTO userDTO = copyToDTO(UserDAOImplTest.getSampleData(firstName, email));
			
			// UPDATE first NAME
			String updatedFirstName = "_first_updated";
			userDTO.setFirst_name(updatedFirstName);

			ObjectMapper om = new ObjectMapper();
			String userJSON = om.writeValueAsString(userDTO);
			
			System.out.println("Request ---> " + userJSON);

			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.put("/v1/user").contentType(MediaType.APPLICATION_JSON).content(userJSON))
					.andExpect(status().isOk()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);

			user = userDAO.getUser(email);
			Assert.isTrue(updatedFirstName.equals(user.getFirstName()));
		} catch (Exception e) {
			 e.printStackTrace();
			 Assert.isTrue(false);
		}
		finally {
			userDAO.delete(email);
		}
	}
	
	@Test
	public void testDelete() throws Exception {
		String firstName = "blah_" + RandomUtils.nextInt();
		String email = firstName + "@blah.com";
		User user = UserDAOImplTest.getSampleData(firstName, email);
		userDAO.save(user);
		

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.delete("/v1/user/email/" + email))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("Response ---> " + content);

		Assert.isNull(userDAO.getUser(email));
	}
	
	protected static UserDTO copyToDTO(User user) {
		UserDTO userDTO = null; 
		if(user != null) {
			userDTO = new UserDTO();
			userDTO.setAddresses(copyToDTO(user.getAddresses()));
			String[] patterns = new String[2];
	        patterns[0] = "yyyy-MM-dd"; //$NON-NLS-1$
	        patterns[1] = "yyyy-MM-dd HH:mm:ss"; //$NON-NLS-1$
	        userDTO.setDob(DateFormatUtils.format(user.getDob(), patterns[0]));
			userDTO.setDocument_ids(user.getDocIds());
			userDTO.setEmail(user.getEmail());
			userDTO.setFirst_name(user.getFirstName());
			userDTO.setLast_name(user.getLastName());
			userDTO.setGender(user.getGender());
			userDTO.setOffices(user.getOffices());
			userDTO.setRoles(user.getRoles());
			userDTO.setSalutation(user.getSalutation());
			
			List<UserAuthHandle> uahs = user.getUserAuthHandles();
			if(!CollectionUtils.isEmpty(uahs)) {
				for (UserAuthHandle userAuthHandle : uahs) {
					if("MOBILE".equalsIgnoreCase(userAuthHandle.getHandleType())) {
						userDTO.setMobile(userAuthHandle.getHandleValue());
					} else if("EMAIL".equalsIgnoreCase(userAuthHandle.getHandleType())) {
						userDTO.setEmail(userAuthHandle.getHandleValue());
					} else if("USERNAME".equalsIgnoreCase(userAuthHandle.getHandleType())) {
						userDTO.setUsername(userAuthHandle.getHandleValue());
					}
				}
			}
		}
		return userDTO;
	} 
	
	protected static AddressDTO copyToDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddress_ln1(address.getAddreessLn1());
		addressDTO.setAddress_ln2(address.getAddreessLn2());
		addressDTO.setAddress_ln3(address.getAddreessLn3());
		addressDTO.setCountry(address.getCountry());
		addressDTO.setState(address.getState());
		addressDTO.setDistrict(address.getDistrict());
		addressDTO.setLandmark(address.getLandmark());
		addressDTO.setLocality(address.getLocality());
		addressDTO.setPincode(address.getPincode());
		addressDTO.setSub_district(address.getSubDistrict());
		addressDTO.setType(address.getType());
		addressDTO.setVtc(address.getVtc());
		return addressDTO;
	}
	
	protected static List<AddressDTO> copyToDTO(List<Address> addresses) {
		List<AddressDTO> addressDTOs = Collections.<AddressDTO>emptyList();
		if(!CollectionUtils.isEmpty(addresses)) {
			addressDTOs = new ArrayList<AddressDTO>(addresses.size());
			for(Address addresse : addresses) {
				addressDTOs.add(copyToDTO(addresse));
			}
		}
		return addressDTOs;
	}
}
