package com.bb.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bb.demo.model.KeyCodeData;
import com.bb.demo.model.KeyCodeStatus;
import com.bb.demo.repository.KeyCodeDataRepository;

/**
 * Sample data loader during startup for demo purpose
 * @author RP
 *
 */
@Component
public class KeyCodeDataLoader implements CommandLineRunner {
    public final KeyCodeDataRepository keyCodeDataRepository;

    public KeyCodeDataLoader(KeyCodeDataRepository keyCodeDataRepository) {
        this.keyCodeDataRepository = keyCodeDataRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	loadKeyCodes();
    }

    private void loadKeyCodes() {
        if (keyCodeDataRepository.count() == 0) {
            keyCodeDataRepository.save(
            		KeyCodeData.builder()
            		.storeId(123456L)
            		.registerId(999999L)
            		.status(KeyCodeStatus.ACTIVE)
            		.keyCode(4567123455L)
                            .build()
            );
            
            keyCodeDataRepository.save(
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

