package com.pruebabackonebox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDTO {

  private Integer productId;
  private Integer quantity;
  private double amount;

}