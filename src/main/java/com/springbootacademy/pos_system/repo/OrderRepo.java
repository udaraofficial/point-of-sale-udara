package com.springbootacademy.pos_system.repo;

import com.springbootacademy.pos_system.dto.queryInterfaces.OrderDetailsInterface;
import com.springbootacademy.pos_system.dto.response.ResponseOrderDetailsDto;
import com.springbootacademy.pos_system.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo  extends JpaRepository<Order,Integer> {

    // Select data by Customer table and  Order table using join query
    @Query(value = "select c.customer_name as customerName, c.customer_address as customerAddress, " +
            "c.contact_number as contactNumber , o.order_date as date, o.total as orderTotal from customer c, orders o where o.active_state= ?1 and c.customer_id = o.customer_id ",nativeQuery = true)
    List<OrderDetailsInterface> getAllOrderDetails(boolean status, Pageable pageable);

    // Count by related status
    @Query(value = "select count(*) from customer c, orders o where o.active_state= ?1 and c.customer_id = o.customer_id",nativeQuery = true)
    long countAllOrderDetails(boolean status);
}
