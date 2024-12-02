package com.springbootacademy.pos_system.service.impl;
import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseItemDto;
import com.springbootacademy.pos_system.dto.request.ItemSaveRequestDto;
import com.springbootacademy.pos_system.dto.response.ItemGetResponseDto;
import com.springbootacademy.pos_system.entity.Item;
import com.springbootacademy.pos_system.repo.ItemRepo;
import com.springbootacademy.pos_system.service.ItemService;
import com.springbootacademy.pos_system.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepo itemRepo;

    // Save Item
    @Override
    public String saveItem(ItemSaveRequestDto itemSaveRequestDto) {
        Item item = modelMapper.map(itemSaveRequestDto, Item.class);
        if(!itemRepo.existsById(item.getId())){
            itemRepo.save(item);
            return item.getItemName()  + " saved successfully";
        }else{
            throw new DuplicateKeyException("Item already exists");
        }
    }
     // Select Item by Name
    @Override
    public List<ItemGetResponseDto> getItemByName(String itemName) {
        boolean status = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName ,status);
        if(items.size()>0){
            List<ItemGetResponseDto> itemGetResponseDtos = modelMapper.map(items,new TypeToken<List<ItemGetResponseDto>>(){

            }.getType());
            return itemGetResponseDtos;
        }else{
            throw new RuntimeException("Item is not active");
        }
    }
   // Select All Items
    @Override
    public List<ItemGetResponseDto> getAllItems() {
        List getAllItems = modelMapper.map(itemRepo.findAll(), List.class);
        List<ItemGetResponseDto> itemsList = new ArrayList<>();
        for(Object item : getAllItems){
        itemsList.add(modelMapper.map(item, ItemGetResponseDto.class));
        }
        return itemsList;
    }
    // Select Item by id
    @Override
    public ItemGetResponseDto getItemById(int itemId) {
        if(itemRepo.existsById(itemId)){
           Item item =itemRepo.getReferenceById(itemId);
           ItemGetResponseDto itemGetResponseDto = modelMapper.map(item,ItemGetResponseDto.class);
           return itemGetResponseDto;
        }
        else {
            throw new RuntimeException("Item is not Found");
        }
    }
     // Select Items by Status
    @Override
    public List<ItemGetResponseDto> getItemsByStatus(boolean activeStatus) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus);
        if(items.size()>0){
            List<ItemGetResponseDto> itemGetResponseDtos = itemMapper.entityListToDtoList(items);
            return itemGetResponseDtos;
        }else{
            throw new RuntimeException("Item is not active");
        }

    }
    // Select Items by related Pagination
    @Override
    public PaginatedResponseItemDto getItemByActiveStateWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));

        if(items.getSize()<1){
            throw  new RuntimeException("No Data found");
        }
        PaginatedResponseItemDto paginatedResponseItemDto = new PaginatedResponseItemDto(
                itemMapper.listDtoToPage(items),
                itemRepo.countAllByActiveStateEquals(activeStatus)
        );
        return paginatedResponseItemDto;
    }
    // Select All Items by Pagination
    @Override
    public PaginatedResponseItemDto getAllItemsByPaginated(int page, int size) {
        Page<Item> getAllItemsByPaginated = itemRepo.findAll(PageRequest.of(page, size));
        return new PaginatedResponseItemDto(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }
    // Select Items by Status
    @Override
    public PaginatedResponseItemDto getAllActiveItemsPaginated(int page, int size, boolean activeState) {
        Page<Item> getAllActiveItemsByPaginated = itemRepo.findAllByActiveStateEquals(activeState,PageRequest.of(page, size));
        return new PaginatedResponseItemDto(
                itemMapper.pageToList(getAllActiveItemsByPaginated),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }

}
