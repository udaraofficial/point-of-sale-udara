package com.springbootacademy.pos_system.controller;

import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos_system.dto.request.RequestOrderSaveDto;
import com.springbootacademy.pos_system.service.OrderService;
import com.springbootacademy.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Save Order
    @PostMapping(path = "save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDto requestOrderSaveDto) {
        String id = orderService.addOrder(requestOrderSaveDto);
        return new ResponseEntity<StandardResponse>(
               new StandardResponse(201,"success" , 2),
                HttpStatus.CREATED
        );
    }
    //Get All Orders Details By Status
    @GetMapping(
            params = {"stateType","page","size"},
            path = {"/get-order-details"}
    )

    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size

    ){
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = null;
        if(stateType.equalsIgnoreCase("active") || stateType.equalsIgnoreCase("inactive")){
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            paginatedResponseOrderDetails = orderService.getAllOrderDetails(status,page,size);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success" , paginatedResponseOrderDetails),
                HttpStatus.OK
        );
    }
}
