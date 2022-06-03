package com.instaclone.instaclone.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;


@Configuration
public class KieConfiguration {

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("rules", "drools-rules", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
        return kContainer;
    }

//    @Bean(name="kieSession")
//    @Scope("prototype")
//    public KieSession kieSession() {
//        return this.kieContainer().newKieSession("testSession");
//    }
//
    @Bean(name = "cepSession")
    public KieSession cepReportSessionRealtimeClock() {
        return this.kieContainer().newKieSession("cepSession");
    }

}
