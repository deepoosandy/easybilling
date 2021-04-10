package com.sanapp.sms.repository;

import com.sanapp.sms.domain.ItemDetailsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<ItemDetailsMaster, Long> {

    ItemDetailsMaster findByItemCode(String itemCode);


}
