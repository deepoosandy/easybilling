package com.sanapp.sms.repository;

import com.sanapp.sms.domain.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ShopUser,Long> {
    ShopUser findByUserName(String username);
}
