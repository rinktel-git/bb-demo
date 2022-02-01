package com.bb.demo.service;

import java.util.List;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.StoreRegisterId;

public interface KeyCodeService {

	List<KeyCodeData> getAllKeyCodeData();

	KeyCodeData getKeyCodeDataById(StoreRegisterId id);
	
	KeyCodeData getKeyCodeDataByKeyCode(Long keyCode);

	KeyCodeData insert(KeyCodeData keyCodeData);

	KeyCodeData updateKeyCodeData(StoreRegisterId id, KeyCodeData keyCodeData);

	KeyCodeData cancelKeyCodeData(StoreRegisterId id, KeyCodeData keyCodeData);

	KeyCodeData cancelKeyCodeDataByKeyCode(Long keyCode);
	
}
