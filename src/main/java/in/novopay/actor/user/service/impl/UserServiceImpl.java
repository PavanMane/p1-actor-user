package in.novopay.actor.user.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import in.novopay.actor.user.dao.Address;
import in.novopay.actor.user.dao.IUserDAO;
import in.novopay.actor.user.dao.User;
import in.novopay.actor.user.dao.UserAuthHandle;
import in.novopay.actor.user.service.AddressDTO;
import in.novopay.actor.user.service.IUserService;
import in.novopay.actor.user.service.UserDTO;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;

	@Override
	public void save(UserDTO userDTO) {
		userDAO.save(copyFromDTO(userDTO));
	}

	@Override
	public void update(UserDTO userDTO) {
		 userDAO.update(copyFromDTO(userDTO));
	}

	@Override
	public void delete(String email) {
		userDAO.delete(email);
	}

	@Override
	public UserDTO getUser(String email) {
		return copyToDTO(userDAO.getUser(email));
	}

	@Override
	public List<UserDTO> getAll() {
		return copyToUserDTO(userDAO.getAll());
	}

	protected static User copyFromDTO(UserDTO userDTO) {
		User user = new User();
		user.setAddresses(copyFromDTO(userDTO.getAddresses()));
		String[] patterns = new String[2];
        patterns[0] = "yyyy-MM-dd"; //$NON-NLS-1$
        patterns[1] = "yyyy-MM-dd HH:mm:ss"; //$NON-NLS-1$
		try {
			user.setDob(DateUtils.parseDate(userDTO.getDob(), patterns));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setDocIds(userDTO.getDocument_ids());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirst_name());
		user.setLastName(userDTO.getLast_name());
		user.setGender(userDTO.getGender());
		user.setLocale("en_IN");
		user.setOffices(userDTO.getOffices());
		user.setRoles(userDTO.getRoles());
		user.setSalutation(userDTO.getSalutation());
		user.setStatus("ACTIVE");
		
		List<UserAuthHandle> uahs = new ArrayList<UserAuthHandle>();
		
		UserAuthHandle mobile = new UserAuthHandle();
		mobile.setHandleType("MOBILE");
		mobile.setHandleValue(userDTO.getMobile());
		mobile.setAuthType("MPIN");
		mobile.setAuthValue(userDTO.getMobile());
		uahs.add(mobile);
		
		UserAuthHandle email = new UserAuthHandle();
		email.setHandleType("EMAIL");
		email.setHandleValue(userDTO.getEmail());
		email.setAuthType("PASSWORD");
		email.setAuthValue(userDTO.getEmail());
		uahs.add(email);
		
		UserAuthHandle username = new UserAuthHandle();
		username.setHandleType("USERNAME");
		username.setHandleValue(userDTO.getUsername());
		username.setAuthType("PASSWORD");
		username.setAuthValue(userDTO.getUsername());
		uahs.add(username);
		
		user.setUserAuthHandles(uahs);
		return user;
	} 
	
	protected static Address copyFromDTO(AddressDTO addressDTO) {
		Address address = new Address();
		address.setAddreessLn1(addressDTO.getAddress_ln1());
		address.setAddreessLn2(addressDTO.getAddress_ln2());
		address.setAddreessLn3(addressDTO.getAddress_ln3());
		address.setAddressGeoCodedLatLong("180.80098::160.897987");
		address.setCountry(addressDTO.getCountry());
		address.setCountryCode(addressDTO.getCountry());
		address.setState(addressDTO.getState());
		address.setStateCode(addressDTO.getState());
		address.setCreatedBy("P1");
		address.setCreatedOn(new Date());
		address.setDistrict(addressDTO.getDistrict());
		address.setDistrictCode(addressDTO.getDistrict());
		address.setLandmark(addressDTO.getLandmark());
		address.setLocality(addressDTO.getLocality());
		address.setPincode(addressDTO.getPincode());
		address.setSubDistrict(addressDTO.getSub_district());
		address.setSubDistrictCode(addressDTO.getSub_district());
		address.setType(addressDTO.getType());
		address.setUpdatedBy("p1");
		address.setUpdatedOn(new Date());
		address.setVtc(addressDTO.getVtc());
		address.setVtcCode(addressDTO.getVtc());
		return address;
	}
	
	protected static List<Address> copyFromDTO(List<AddressDTO> addressDTOs) {
		List<Address> addresses = Collections.<Address>emptyList();
		if(!CollectionUtils.isEmpty(addressDTOs)) {
			addresses = new ArrayList<Address>(addressDTOs.size());
			for(AddressDTO addressDTO : addressDTOs) {
				addresses.add(copyFromDTO(addressDTO));
			}
		}
		return addresses;
	}
	
	protected static List<UserDTO> copyToUserDTO(List<User> users) {
		List<UserDTO> userDTOs = Collections.<UserDTO>emptyList();
		if(!CollectionUtils.isEmpty(users)) {
			userDTOs = new ArrayList<UserDTO>(users.size());
			for(User user : users) {
				userDTOs.add(copyToDTO(user));
			}
		}
		return userDTOs;
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
