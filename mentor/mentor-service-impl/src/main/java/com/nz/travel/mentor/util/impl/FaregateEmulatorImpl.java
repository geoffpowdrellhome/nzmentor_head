package com.nz.travel.mentor.util.impl;

import com.nz.travel.mentor.model.impl.User;
import net.flitech.faregate.FaregateEmulator;
import net.flitech.faregate.FaregateLoginDetails;
import net.flitech.faregate.air.Airline;
import net.flitech.faregate.air.translator.impl.FaregateTranslator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.nz.travel.mentor.enums.GalileoSettings.*;

/**
 * Emulates the session to a pseudo.

 */
@Component
public class FaregateEmulatorImpl extends FaregateTranslator implements FaregateEmulator {

    private static final String AGENT_CODE = "AG";

    @Resource(name="userProviderSettingsDAO")
    private UserProviderSettingsDAO userProviderSettingsDAO;

    @Override
    public boolean isEmulationEnabled(User user) {
        Boolean enabled = userProviderSettingsDAO.getProperty(user.getId(), SESSION_EMULATION_ENABLED, Boolean.class, false);
        if (logger.isDebugEnabled()) {
            logger.debug("emulation enabled=" + enabled);
        }

        return enabled;
    }

    @Override
    public FaregateLoginDetails findLoginDetails(User user, Airline airline) {
        String username = userProviderSettingsDAO.getProperty(user.getId(), SESSION_EMULATION_USERNAME, String.class);
        String password = userProviderSettingsDAO.getProperty(user.getId(), SESSION_EMULATION_PASSWORD, String.class);
        String pseudo = userProviderSettingsDAO.getProperty(user.getId(), SESSION_EMULATION_PSEUDO, String.class);

        FaregateLoginDetails details = new FaregateLoginDetails(username, password, pseudo, airline.getPseudo());
        if (logger.isDebugEnabled()) {
            logger.debug("login details=" + details);
        }

        return details;
    }

}

