package com.instaclone.instaclone.service;

import com.instaclone.instaclone.model.Item;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

    private final KieContainer kieContainer;
   
    
    public Item getClassifiedItem(Item i) {
        KieSession kieSession = kieContainer.newKieSession("testSession");
        kieSession.insert(i);
        kieSession.fireAllRules();
        kieSession.dispose();
        return i;
    }
}