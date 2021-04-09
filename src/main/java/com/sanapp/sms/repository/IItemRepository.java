package com.sanapp.sms.repository;

import com.sanapp.sms.domain.ItemDetailsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<ItemDetailsMaster,Long> {
    ItemDetailsMaster findByItemCode(String itemCode);

    @Query(value="SELECT * FROM item_details_master im, unit_price_t up WHERE up.item_code = im.item_code\n" +
            "AND NOW() BETWEEN up.eff_dt AND up.thru_dt", nativeQuery = true)
    List<ItemDetailsMaster> listAllItems();

    @Query(value="SELECT * FROM item_details_master im, unit_price_t up WHERE up.item_code = im.item_code\n" +
            "AND NOW() BETWEEN up.eff_dt AND up.thru_dt and up.item_code=?1", nativeQuery = true)
    ItemDetailsMaster    itembyItemCode(String item);



}
