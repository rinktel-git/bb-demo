package com.bb.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.KeyCodeStatus;
import com.bb.demo.model.StoreRegisterId;
import com.bb.demo.repository.KeyCodeDataRepository;

@SpringBootTest
class KeyCodeServiceTest {

	@Autowired
	KeyCodeDataRepository repo;
	
	@Autowired
	KeyCodeService keyCodeService;
	
	@BeforeTestClass
	void loadData() {
		loadKeyCodes();
	}
	
	@Test
	void testInsert() {
		KeyCodeData keyCodeData = new KeyCodeData();
		keyCodeData.setStoreId(88998899L);
		keyCodeData.setRegisterId(55556666L);
		
		KeyCodeData kc = keyCodeService.insert(keyCodeData);
		Long kcl = repo.findByKeyCode(kc.getKeyCode()).get().getKeyCode();
		assertTrue(kcl.longValue() == kc.getKeyCode().longValue());
		
	}
	
	@Test
	void testInsertFail() {
		KeyCodeData keyCodeData = new KeyCodeData();
		keyCodeData.setStoreId(88998899L);
		keyCodeData.setRegisterId(null);
		
		Assertions.assertThrows(Exception.class, () -> {
			KeyCodeData kc = keyCodeService.insert(keyCodeData);
			Long kcl = repo.findByKeyCode(kc.getKeyCode()).get().getKeyCode();
		}, "Exception was expected");
		
	}
	
	@Test
	void testInsertFail1() {
		KeyCodeData keyCodeData = new KeyCodeData();
		keyCodeData.setStoreId(null);
		keyCodeData.setRegisterId(88998899L);
		
		Assertions.assertThrows(Exception.class, () -> {
			KeyCodeData kc = keyCodeService.insert(keyCodeData);
			Long kcl = repo.findByKeyCode(kc.getKeyCode()).get().getKeyCode();
		}, "Exception was expected");
		
	}
	
	
	@Test
	void testGetAllKeyCodeData() {
		assertTrue(keyCodeService.getAllKeyCodeData().size() == 2);
	}

	@Test
	void testGetKeyCodeDataById() {
		StoreRegisterId storeRegisterId = new StoreRegisterId();
		storeRegisterId.setStoreId(123456L);
		storeRegisterId.setRegisterId(999999L);
		
		KeyCodeData kc = keyCodeService.getKeyCodeDataById(storeRegisterId);
		System.out.print("****************" + kc.getKeyCode().longValue());
		assertTrue(4567123455L == kc.getKeyCode().longValue());
	}

	@Test
	void testGetKeyCodeDataByIdFail() {
		StoreRegisterId storeRegisterId = new StoreRegisterId();
		storeRegisterId.setStoreId(123456L);
		storeRegisterId.setRegisterId(999995559L);
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			KeyCodeData kc = keyCodeService.getKeyCodeDataById(storeRegisterId);
		}, "EntityNotFoundException was expected");
		
	}
	

	@Test
	void testUpdateKeyCodeData() {
		StoreRegisterId storeRegisterId = new StoreRegisterId();
		storeRegisterId.setStoreId(9876543L);
		storeRegisterId.setRegisterId(8888888L);
		KeyCodeData k = new KeyCodeData();
		k.setKeyCode(7766776677L);
		KeyCodeData kc = keyCodeService.updateKeyCodeData(storeRegisterId, k);
		
		KeyCodeData kc1 = keyCodeService.getKeyCodeDataById(storeRegisterId);
		assertTrue(7766776677L == kc1.getKeyCode().longValue());
		
	}
	

	private void loadKeyCodes() {
        if (repo.count() == 0) {
            repo.save(
            		KeyCodeData.builder()
            		.storeId(123456L)
            		.registerId(999999L)
            		.status(KeyCodeStatus.ACTIVE)
            		.keyCode(4567123455L)
                            .build()
            );
            
            repo.save(
            		KeyCodeData.builder()
            		.storeId(9876543L)
            		.registerId(8888888L)
            		.status(KeyCodeStatus.ACTIVE)
            		.keyCode(3423454462L)
                            .build()
            );
            
            System.out.println("Sample data Loaded");
        }
    }
}
