package com.springbootacademy.pos_system.service.impl;

import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos_system.dto.queryInterfaces.OrderDetailsInterface;
import com.springbootacademy.pos_system.dto.request.RequestOrderSaveDto;
import com.springbootacademy.pos_system.dto.response.ResponseOrderDetailsDto;
import com.springbootacademy.pos_system.entity.Order;
import com.springbootacademy.pos_system.entity.OrderDetails;
import com.springbootacademy.pos_system.repo.CustomerRepo;
import com.springbootacademy.pos_system.repo.ItemRepo;
import com.springbootacademy.pos_system.repo.OrderDetailsRepo;
import com.springbootacademy.pos_system.repo.OrderRepo;
import com.springbootacademy.pos_system.service.OrderService;
import com.springbootacademy.pos_system.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    @Transactional
    // Save Order
    public String addOrder(RequestOrderSaveDto requestOrderSaveDto) {

        // order Table
        Order order = new Order(
                customerRepo.getReferenceById(requestOrderSaveDto.getCustomer()),
                requestOrderSaveDto.getDate(),
                requestOrderSaveDto.getTotal()
        );

        orderRepo.save(order);
       // order Details Table
        if(orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper.map(requestOrderSaveDto.getOrderDetails(),
                    new TypeToken<List<OrderDetails>>(){}.getType());

            for(int i=0; i<orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(requestOrderSaveDto.getOrderDetails().get(i).getItems()));
            }

            if(orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }
    // Select All Order Details
    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
       List<OrderDetailsInterface> orderDetailsDtos = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));
       List <ResponseOrderDetailsDto> list = new ArrayList<>();
       for(OrderDetailsInterface orderDetailsDto : orderDetailsDtos) {
           ResponseOrderDetailsDto responseOrderDetailsDto = new ResponseOrderDetailsDto(
                   orderDetailsDto.getCustomerName(),
                   orderDetailsDto.getCustomerAddress(),
                   orderDetailsDto.getContactNumber(),
                   orderDetailsDto.getDate(),
                   orderDetailsDto.getOrderTotal()
           );
           list.add(responseOrderDetailsDto );
       }
       PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
               list,
               orderRepo.countAllOrderDetails(status)
       );
       return paginatedResponseOrderDetails;

    }

}
