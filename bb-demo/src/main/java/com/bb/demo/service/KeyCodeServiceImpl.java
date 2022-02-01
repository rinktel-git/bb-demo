package com.bb.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.KeyCodeStatus;
import com.bb.demo.model.StoreRegisterId;
import com.bb.demo.repository.KeyCodeDataRepository;
import com.bb.demo.util.KeyCodeServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class
 * @author RP
 *
 */
@Service
@Slf4j
public class KeyCodeServiceImpl implements KeyCodeService {

	@Autowired
	KeyCodeDataRepository keyCodeDataRepository;

	@Override
	public List<KeyCodeData> getAllKeyCodeData() {
		List<KeyCodeData> keyCodes = new ArrayList<>();

		keyCodeDataRepository.findAll().forEach(keyCodes::add);

		return keyCodes;
	}

	@Override
	public KeyCodeData getKeyCodeDataById(StoreRegisterId id) {

		log.info("Inside getKeyCodeDataById {} {}", id.getRegisterId(), id.getStoreId());
		return keyCodeDataRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

	}

	@Override
	public KeyCodeData insert(KeyCodeData keyCodeData) {

		KeyCodeData kc = null;
		keyCodeData.setKeyCode(KeyCodeServiceUtil.generateRandomDigits());
		keyCodeData.setStatus(KeyCodeStatus.ACTIVE);
		try {
			kc = keyCodeDataRepository.save(keyCodeData);
		} catch(Exception e) {
			log.error("Error while saving record", e);
			throw e;
		}

		return kc;
	}

	@Override
	public KeyCodeData updateKeyCodeData(StoreRegisterId id, KeyCodeData keyCode) {

		KeyCodeData kcUpdated = null;
		KeyCodeData kc = keyCodeDataRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		kc.setKeyCode(keyCode.getKeyCode());
		try {
			kcUpdated = keyCodeDataRepository.save(kc);
		} catch(Exception e) {
			log.error("Error while saving record", e);
			throw e;
		}
		return kcUpdated;

	}

	@Override
	public KeyCodeData cancelKeyCodeData(StoreRegisterId id, KeyCodeData keyCode) {

		KeyCodeData kc = keyCodeDataRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		kc.setStatus(KeyCodeStatus.INACTIVE);
		KeyCodeData cancelled = keyCodeDataRepository.save(kc);

		return cancelled;
	}

	@Override
	public KeyCodeData cancelKeyCodeDataByKeyCode(Long keyCode) {

		KeyCodeData kc = getKeyCodeDataByKeyCode(keyCode);
		kc.setStatus(KeyCodeStatus.INACTIVE);
		KeyCodeData cancelled = keyCodeDataRepository.save(kc);

		return cancelled;
	}

	@Override
	public KeyCodeData getKeyCodeDataByKeyCode(Long keyCode) {
		log.info("Inside getKeyCodeDataByKeyCode {}", keyCode);
		return keyCodeDataRepository.findByKeyCode(keyCode)
				.orElseThrow(() -> new EntityNotFoundException());
	}

}
