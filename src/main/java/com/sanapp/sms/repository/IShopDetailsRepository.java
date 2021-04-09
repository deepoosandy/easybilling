package com.sanapp.sms.repository;

import com.sanapp.sms.domain.ShopDetailsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopDetailsRepository extends JpaRepository<ShopDetailsMaster, Long> {
}
