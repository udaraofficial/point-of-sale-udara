package com.springbootacademy.pos_system.util.mappers;

import com.springbootacademy.pos_system.dto.response.ItemGetResponseDto;
import com.springbootacademy.pos_system.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<ItemGetResponseDto> entityListToDtoList(List<Item> items);

    List<ItemGetResponseDto> listDtoToPage(Page<Item> items);

    List<ItemGetResponseDto> pageToList(Page<Item> getAllItemsByPaginated);
}
