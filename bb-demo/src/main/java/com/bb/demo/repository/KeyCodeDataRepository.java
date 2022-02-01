package com.bb.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.StoreRegisterId;

@Repository
public interface KeyCodeDataRepository extends CrudRepository<KeyCodeData, StoreRegisterId> {

	List<KeyCodeData> findByStoreId(Long storeId);
	
	List<KeyCodeData> findByRegisterId(Long registerId);

	@Query("SELECT k FROM KeyCodeData k WHERE k.keyCode = :keyCode")
	Optional<KeyCodeData> findByKeyCode(Long keyCode);
	
}
