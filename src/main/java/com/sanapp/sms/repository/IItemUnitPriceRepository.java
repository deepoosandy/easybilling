package com.sanapp.sms.repository;

import com.sanapp.sms.domain.UnitPriceT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemUnitPriceRepository extends JpaRepository<UnitPriceT,Long> {

    @Query(value="SELECT * FROM unit_price_t up WHERE up.item_code =?1" +
            "AND NOW() BETWEEN up.eff_dt AND up.thru_dt", nativeQuery = true)
    UnitPriceT fetchItem(String ItemCode);
}
