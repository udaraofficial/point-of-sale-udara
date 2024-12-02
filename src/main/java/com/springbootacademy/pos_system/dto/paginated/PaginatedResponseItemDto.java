package com.springbootacademy.pos_system.dto.paginated;

import com.springbootacademy.pos_system.dto.response.ItemGetResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDto {
  List<ItemGetResponseDto> list;
  private long dataCount;

}
