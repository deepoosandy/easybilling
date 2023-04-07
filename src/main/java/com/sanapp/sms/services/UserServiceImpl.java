package com.sanapp.sms.services;

import com.sanapp.sms.domain.ShopUser;
import com.sanapp.sms.dto.SystemUserDetails;
import com.sanapp.sms.dto.UserDto;
import com.sanapp.sms.repository.UserRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsManager {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

   // @Override
    @Transactional
    public UserDto findByUsername(String username) {
        return ItemDetailsMapper.domainToDto(userRepository.findByUserName(username));
    }

   // @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        userDto.setRole("ROLE_USER");
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setEnabled(Boolean.TRUE);
        return ItemDetailsMapper.domainToDto(
                userRepository.save(ItemDetailsMapper.dtoToDomain(userDto)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser shopUser = userRepository.findByUserName(username);
        if (shopUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }


        /* new org.springframework.security.core.userdetails.User(shopUser.getUserName(), shopUser.getPassword(),
                mapRolesToAuthorities(shopUser.getRole()));*/
        return new SystemUserDetails(ItemDetailsMapper.domainToDto(shopUser));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        return roles.stream().map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
