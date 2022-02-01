package com.bb.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(StoreRegisterId.class)
public class KeyCodeData {

	@Id
	private Long storeId;

	@Id
	private Long registerId;
	
	@Column
	Long keyCode;

	@Column
	KeyCodeStatus status;

	@CreationTimestamp
	@Column(updatable = false)
	Timestamp dateCreated;

	@UpdateTimestamp
	Timestamp lastModified;

	@Override
	public String toString() {
		return "KeyCodeData [storeId=" + storeId + ", registerId=" + registerId + ", keyCode=" + keyCode + ", status="
				+ status + "]";
	}

}
