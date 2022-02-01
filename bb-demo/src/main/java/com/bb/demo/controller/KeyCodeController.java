package com.bb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.StoreRegisterId;
import com.bb.demo.service.KeyCodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Main Controller for BB-Demo KeyCode Service
 * @author 
 *
 */
@RestController
@RequestMapping("/api/v1/kc")
@Slf4j
public class KeyCodeController {

	@Autowired
	KeyCodeService keyCodeService;

	@GetMapping("/all")
	public ResponseEntity<List<KeyCodeData>> getAllKeyCodes() {
		List<KeyCodeData> KeyCodes = keyCodeService.getAllKeyCodeData();
		return new ResponseEntity<>(KeyCodes, HttpStatus.OK);
	}

	@GetMapping({"/{storeRegisterId}"})
	public ResponseEntity<KeyCodeData> getKeyCode(@PathVariable String storeRegisterId) {

		String[] ids = storeRegisterId.split("-");
		Long storeId = Long.valueOf(((String) ids[0]));
		Long registerId = Long.valueOf(((String) ids[1]));
		StoreRegisterId id = new StoreRegisterId(storeId, registerId);
		return new ResponseEntity<>(keyCodeService.getKeyCodeDataById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<KeyCodeData> getKeyCode(@RequestBody KeyCodeData keyCodeData) {

		KeyCodeData kc = null;
		if(keyCodeData != null && keyCodeData.getRegisterId() != null && keyCodeData.getStoreId() != null) {
			StoreRegisterId id = new StoreRegisterId(keyCodeData.getStoreId(), keyCodeData.getRegisterId());
			kc = keyCodeService.getKeyCodeDataById(id);
		} else if (keyCodeData != null && keyCodeData.getKeyCode() != null) {
			kc = keyCodeService.getKeyCodeDataByKeyCode(keyCodeData.getKeyCode());
		} else {
			log.warn("Invalid data {}", keyCodeData);
			return new ResponseEntity<>(kc, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(kc, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<KeyCodeData> saveKeyCode(@RequestBody KeyCodeData keyCodeData) {

		KeyCodeData keyCodeDataResponse = keyCodeService.insert(keyCodeData);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("keyCodeData", "/api/v1/kc/" + keyCodeDataResponse.getStoreId().toString());
		return new ResponseEntity<>(keyCodeDataResponse, httpHeaders, HttpStatus.CREATED);
	}


	@PutMapping({"/{storeRegisterId}"})
	public ResponseEntity<KeyCodeData> updateKeyCodeData(@PathVariable("storeRegisterId") String storeRegisterId, @RequestBody KeyCodeData keyCodeData) {
		String[] ids = storeRegisterId.split("-");
		Long storeId = Long.valueOf(((String) ids[0]));
		Long registerId = Long.valueOf(((String) ids[1]));
		StoreRegisterId id = new StoreRegisterId(storeId, registerId);
		keyCodeService.updateKeyCodeData(id, keyCodeData);
		return new ResponseEntity<>(keyCodeService.getKeyCodeDataById(id), HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<KeyCodeData> updateKeyCodeData(@RequestBody KeyCodeData keyCodeData) {
		StoreRegisterId id = new StoreRegisterId(keyCodeData.getStoreId(), keyCodeData.getRegisterId());
		keyCodeService.updateKeyCodeData(id, keyCodeData);
		return new ResponseEntity<>(keyCodeService.getKeyCodeDataById(id), HttpStatus.OK);
	}

	@PutMapping("/cancel")
	public ResponseEntity<KeyCodeData> cancelKeyCodeData(@RequestBody KeyCodeData keyCodeData) {
		
		KeyCodeData kc = null;
		if(keyCodeData != null && keyCodeData.getRegisterId() != null && keyCodeData.getStoreId() != null) {
			StoreRegisterId id = new StoreRegisterId(keyCodeData.getStoreId(), keyCodeData.getRegisterId());
			kc = keyCodeService.cancelKeyCodeData(id, keyCodeData);
		} else if (keyCodeData != null && keyCodeData.getKeyCode() != null) {
			kc = keyCodeService.cancelKeyCodeDataByKeyCode(keyCodeData.getKeyCode());
		} else {
			log.warn("Invalid data {}", keyCodeData);
			return new ResponseEntity<>(kc, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(kc, HttpStatus.OK);
	}

}
