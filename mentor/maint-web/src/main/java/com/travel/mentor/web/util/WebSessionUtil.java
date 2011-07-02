package com.travel.mentor.web.util;

import com.travel.mentor.dao.dto.impl.UserDTO;
import com.travel.mentor.dao.dto.impl.UserSessionCookieDTO;
import com.travel.mentor.service.UserService;
import com.travel.mentor.service.type.AccommodationSiteTypeService;
import com.travel.mentor.web.core.SessionHelper;
import com.travel.mentor.web.core.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class WebSessionUtil {

    private SessionHelper sessionHelper;

    @Resource(name = "userService")
    protected UserService userService;

    @Resource(name = "accommodationSiteTypeService")
    protected AccommodationSiteTypeService accommodationSiteTypeService;

    protected String webDavServerUrl;
    protected String imageDirUrl;

    public String getWebDavServerUrl() {
        return webDavServerUrl;
    }

    public void setWebDavServerUrl(String webDavServerUrl) {
        this.webDavServerUrl = webDavServerUrl;
    }

    public String getImageDirUrl() {
        return imageDirUrl;
    }

    public void setImageDirUrl(String imageDirUrl) {
        this.imageDirUrl = imageDirUrl;
    }

    public UserSessionCookieDTO getUserSessionCookieDTO() {
        if ((SecurityContextHolder.getContext() == null)
                || (SecurityContextHolder.getContext().getAuthentication() == null)
                || (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())) {

            return null;
        }

        UserSessionCookieDTO userSessionCookieDTO = sessionHelper.get(SessionKey.USER_SESSION_COOKIE);
        if (userSessionCookieDTO == null || (!userSessionCookieDTO.getUserDTO().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))) {
            userSessionCookieDTO = insertUserSessionCookie();
        }

        return userSessionCookieDTO;
    }

    public UserSessionCookieDTO insertUserSessionCookie() {

        UserDTO userDTO = userService.find(SecurityContextHolder.getContext().getAuthentication().getName());

        UserSessionCookieDTO userSessionCookieDTO = new UserSessionCookieDTO();
        userSessionCookieDTO.setSessionId(UUID.randomUUID().toString());
        userSessionCookieDTO.setUserDTO(userDTO);
        sessionHelper.set(SessionKey.USER_SESSION_COOKIE, userSessionCookieDTO);

        return userSessionCookieDTO;
    }


    /**
     * @method getServerImageDirUrl()
     * @returns String
     */
    public String getServerImageDirUrl() {
        StringBuffer sBuf = new StringBuffer();
        sBuf.append(webDavServerUrl);
        sBuf.append(imageDirUrl);
        return sBuf.toString();
    }

    @Autowired
    public void setSessionHelper(SessionHelper sessionHelper) {
        this.sessionHelper = sessionHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

}

