package com.springbootacademy.pos_system.service;

import com.springbootacademy.pos_system.dto.paginated.PaginatedResponseItemDto;
import com.springbootacademy.pos_system.dto.request.ItemSaveRequestDto;
import com.springbootacademy.pos_system.dto.response.ItemGetResponseDto;

import javax.validation.constraints.Max;
import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDto itemSaveRequestDto);

    List<ItemGetResponseDto> getItemByName(String itemName);

    List<ItemGetResponseDto> getAllItems();

    ItemGetResponseDto getItemById(int itemId);

    List<ItemGetResponseDto> getItemsByStatus(boolean activeStatus);

    PaginatedResponseItemDto getItemByActiveStateWithPaginated(boolean activeStatus, int page, int size);

    PaginatedResponseItemDto getAllItemsByPaginated(int page, int size);

    PaginatedResponseItemDto getAllActiveItemsPaginated(int page, @Max(50) int size, boolean activeState);
}
