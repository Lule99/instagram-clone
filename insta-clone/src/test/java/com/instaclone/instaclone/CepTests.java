package com.instaclone.instaclone;

import com.instaclone.instaclone.events.LongScrollingEvent;
import com.instaclone.instaclone.events.ReactionEvent;
import com.instaclone.instaclone.events.ReloadEvent;
import com.instaclone.instaclone.events.UpdateParametersEvent;
import com.instaclone.instaclone.model.User;
import com.instaclone.instaclone.service.CategorizationService;
import com.instaclone.instaclone.service.UserService;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CepTests extends InstaCloneApplicationTests {

    private KieSession cepKieSession;

    @Autowired
    private UserService userService;
    
    @Test
    public void reloadWithReaction_shouldNotCreateUPEvent() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        cepKieSession = kc.newKieSession("cepSession");

        User user = userService.findByUsername("regularUsername1");
        User user2 = userService.findByUsername("regularUsername3");
        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));

        int ruleCount = cepKieSession.fireAllRules();
        assertThat(ruleCount, equalTo(1));

        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));
        cepKieSession.fireAllRules();

        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));
        cepKieSession.fireAllRules();

        QueryResults results = cepKieSession.getQueryResults("getUpdateParametersEvent");
        assertThat(results.size(), equalTo(0));

        cepKieSession.insert(new ReactionEvent(user.getProfile(), user2.getProfile()));
        cepKieSession.fireAllRules();


        results = cepKieSession.getQueryResults("getUpdateParametersEvent");
        assertThat(results.size(), equalTo(0));
        cepKieSession.dispose();
    }

    @Test
    public void reloadWithLongScEvent_shouldCreateUPEvent() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        cepKieSession = kc.newKieSession("cepSession");

        User user = userService.findByUsername("regularUsername1");

        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));
        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));
        cepKieSession.insert(new ReloadEvent(user.getProfile(), false));
        cepKieSession.insert(new LongScrollingEvent(user.getProfile(), false));
        cepKieSession.fireAllRules();

        QueryResults results = cepKieSession.getQueryResults("getUpdateParametersEvent");
        assertThat(results.size(), equalTo(1));
        cepKieSession.dispose();
    }

    @Test
    public void twoUPEvents_shouldCreateCCPEvent() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        cepKieSession = kc.newKieSession("cepSession");

        User user = userService.findByUsername("regularUsername1");

        cepKieSession.insert(new UpdateParametersEvent(user.getProfile(), true));
        cepKieSession.insert(new UpdateParametersEvent(user.getProfile(), true));
        cepKieSession.fireAllRules();

        QueryResults results = cepKieSession.getQueryResults("getChangeCompleteParametersEvent");
        assertThat(results.size(), equalTo(1));
        cepKieSession.dispose();
    }
}
