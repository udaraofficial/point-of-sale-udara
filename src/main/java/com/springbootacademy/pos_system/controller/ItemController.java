package com.springbootacademy.pos_system.controller;
import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseItemDto;
import com.springbootacademy.pos_system.dto.request.ItemSaveRequestDto;
import com.springbootacademy.pos_system.dto.response.ItemGetResponseDto;
import com.springbootacademy.pos_system.service.ItemService;
import com.springbootacademy.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Save Item
    @PostMapping(
            path = "/save"
    )

    public ResponseEntity<StandardResponse> saveItem (@RequestBody ItemSaveRequestDto itemSaveRequestDto) {
        String message = itemService.saveItem(itemSaveRequestDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }
    // Select Item by Name
    @GetMapping(
            path = "getByName",
            params = "name"
    )

    public ResponseEntity<StandardResponse> getItemByName(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDto> itemDto = itemService.getItemByName(itemName);
        return new ResponseEntity<StandardResponse>(
               new StandardResponse(200,"Success" , itemDto),
               HttpStatus.OK
        );
    }

    // Select All items
    @GetMapping(
            path = "/getAll"
    )

    public ResponseEntity<StandardResponse>getAllItems() {
        List<ItemGetResponseDto> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Sucess" , allItems),
                HttpStatus.OK
        );
    }
    // Select specific Item
    @GetMapping(
            path = "/get",
            params = "id"
    )

    public ResponseEntity<StandardResponse> getItemById(@RequestParam(value = "id") int itemId) {
        ItemGetResponseDto itemGetResponseDto = itemService.getItemById(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" ,itemGetResponseDto),
                HttpStatus.OK
        );
    }
     // Select Items by status
    @GetMapping(
            path = "/get-all-items-by-status",
            params = "activeStatus"
    )

    public ResponseEntity<StandardResponse>getItemsByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseItemDto paginatedResponseItemDto = itemService.getItemByActiveStateWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
             new StandardResponse(200,"Success" , paginatedResponseItemDto),
             HttpStatus.OK
     );
    }
    // Select all items by Pagination
    @GetMapping(
            path = "/get-all-items-paginated",
            params ={"page","size"}

    )

    public ResponseEntity<StandardResponse>getAllItemsByPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size

            ){
            PaginatedResponseItemDto paginatedResponseItemDto = itemService.getAllItemsByPaginated(page,size);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200,"Success" , paginatedResponseItemDto),
                    HttpStatus.OK

            );

    }
    // Select all active items by status
    @GetMapping (
            path = "get-all-active-items-paginated",
            params = {"page","size","activeState"}
    )

    public ResponseEntity<StandardResponse>getAllActiveItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState

    ){
        PaginatedResponseItemDto paginatedResponseItemDto = itemService.getAllActiveItemsPaginated(page,size ,activeState);
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" ,paginatedResponseItemDto),
                HttpStatus.OK
        );
    }


}
