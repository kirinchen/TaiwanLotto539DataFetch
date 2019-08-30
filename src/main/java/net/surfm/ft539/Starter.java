package net.surfm.ft539;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Starter implements ApplicationListener<ApplicationReadyEvent> {

	@Inject
	private MinerSetuper minerSetuper;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("onApplicationEvent:" + minerSetuper);
		try {
			minerSetuper.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
