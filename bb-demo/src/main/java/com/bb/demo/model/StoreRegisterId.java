package com.bb.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterId implements Serializable {

	private Long storeId;

    private Long registerId;

    
}
