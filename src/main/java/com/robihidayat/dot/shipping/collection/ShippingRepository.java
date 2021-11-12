package com.robihidayat.dot.shipping.collection;

import com.robihidayat.dot.shipping.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long > {

}
