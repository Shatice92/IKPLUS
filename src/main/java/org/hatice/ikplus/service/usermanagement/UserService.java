package org.hatice.ikplus.service.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.entity.usermanagement.UserRole;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.usermapper.UserMapper;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.hatice.ikplus.repository.usermanagement.RoleRepository;
import org.hatice.ikplus.repository.usermanagement.UserRoleRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;
	private final UserRoleRepository userRoleRepository;
	
	public void save(@Valid SaveUserRequestDto dto) {
		User user = userMapper.fromSaveUserDto(dto);
		userRepository.save(user);
	}
	
	public List<VwUser> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.orElse(null);
	}
	
	public void register(@Valid RegisterRequestDto dto) {
		User user = userMapper.fromRegisterDto(dto);
		userRepository.save(user);
	}
	
	public User login(@Valid LoginRequestDto dto) {
		Optional<User> userOptional = userRepository.findByEmail(dto.email());
		
		if (userOptional.isEmpty()) {
			throw new IKPlusException(ErrorType.USER_NOT_FOUND);
		}
		
		User user = userOptional.get();
		
		if (!user.getPassword().equals(dto.password())) {
			throw new IKPlusException(ErrorType.INVALID_CREDENTIALS);
		}
		
		return user;
	}
	
	
	public void assignRoleToUser(Long userId, RoleName roleName) {
		Optional<User> userOptional = userRepository.findById(userId);
		Optional<Role> roleOptional = roleRepository.findByName(roleName);
		
		if (userOptional.isPresent() && roleOptional.isPresent()) {
			UserRole userRole = UserRole.builder()
			                            .userId(userId)
			                            .roleId(roleOptional.get().getId())
			                            .build();
			userRoleRepository.save(userRole);
		} else {
			throw new IKPlusException(ErrorType.ENTITY_NOT_FOUND);
		}
	}
}