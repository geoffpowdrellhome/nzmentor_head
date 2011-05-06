package net.flitech.faregate.service.auth;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.springframework.stereotype.Component;

@Component
public class PasswordCallback implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
		if (callbacks==null) return;
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
				// username token pwd...
				pc.setIdentifier("default");
				pc.setPassword("default");
			}
		}
	}
}
