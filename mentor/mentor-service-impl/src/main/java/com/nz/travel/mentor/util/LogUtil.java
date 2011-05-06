package com.nz.travel.mentor.util;

import com.nz.travel.mentor.model.impl.User;
import net.flitech.faregate.air.AirlineDetails;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import javax.xml.bind.JAXBElement;
import java.io.StringWriter;
import java.util.*;

import static net.flitech.ParamChecker.isNullOrEmpty;

// TODO: embed a LogUtil in MentorObject, or like TestCase have MentorObject extend LogUtil.

/**
 * Utility class for logging.
 *
 * @author Ben Warren
 * @see LogUtilUnitTest
 */
public final class LogUtil {

    public static final String NULL = "<null>";
    public static final XmlOptions DEFAULT_XML_OPTIONS = createDefaultXmlOptions();
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String NULL_STRING = "null";
    private static final String LIST_SEPARATOR = ", ";
    private static final String SQUARE_BRACKET_RIGHT = "]";
    private static final String SQUARE_BRACKET_LEFT = "[";
    private static final ToStringStyle TO_STRING_STYLE = new FaregateToStringStyle();
    private static final String PACKAGE_VIRGINBLUE = "au.com.virginblue";
    private static final String PACKAGE_FAREGATE = "net.flitech.faregate";
    private static final String PACKAGE_JETSTAR = "com.jetstar";
    private static final String UNDERSCORES = "__";
    private static final List<String> ALLOWED_PACKAGES = createAllowedPackages();

    private static List<String> createAllowedPackages() {
        List<String> list = new ArrayList<String>();
        list.add(PACKAGE_FAREGATE);
        list.add(PACKAGE_VIRGINBLUE);
        list.add(PACKAGE_JETSTAR);
        return list;
    }

    // TODO: instead of hard-coding the packages, get the first part of the package from the value
    // Object and use that, ie. net.flitech.faregate or com.jetstar.booking. Then put in GSA-Utils.
    private static boolean isFaregateObject(Object value) {
        if (value == null) return false;
        boolean isAllowed = false;
        for (Object element : ALLOWED_PACKAGES) {
            if (value.getClass().getName().indexOf((String) element) >= 0) {
                isAllowed = true;
            }
        }
        return isAllowed;
    }

    private LogUtil() {
    }


    public static void logResponse(final Object response, final String message, final Logger logger,
                                   final Level level, long requestId) {
        if (logger.isEnabledFor(level)) {
            String idMessage = createMessageWithId(requestId, message);
            logger.log(level, idMessage + getString(response));
        }
    }

    public static long logRequest(Object request, String message, Logger logger, Level level) {
        long requestId = 0;
        if (logger.isEnabledFor(level)) {
            requestId = createNewRequestId();
            logger.log(level, createMessageWithId(requestId, message) + getString(request));
        }
        return requestId;
    }

    public static String getString(JAXBElement jaxbObject) {
        if (jaxbObject == null) return NULL_STRING;
        String className = jaxbObject.getClass().getSimpleName();
        return className + SQUARE_BRACKET_LEFT + jaxbObject.toString();
    }

//    public static void logResponse(final XmlObject response, final String message, final Logger logger,
//            final Level level, long requestId) {
//        if (logger.isEnabledFor(level)) {
//            String idMessage = createMessageWithId(requestId, message);
//            logger.log(level, idMessage + getString(response));
//        }
//    }


//    public static long logRequest(XmlObject request, String message, Logger logger, Level level) {
//        long requestId = 0;
//        if (logger.isEnabledFor(level)) {
//            requestId = createNewRequestId();
//            logger.log(level, createMessageWithId(requestId, message) + getString(request));
//        }
//        return requestId;
//    }

//    public static String getString(XmlObject xmlObject) {
//        if (xmlObject == null) return NULL_STRING;
//        String className = xmlObject.getClass().getSimpleName();
//        return className + SQUARE_BRACKET_LEFT + xmlObject.xmlText(DEFAULT_XML_OPTIONS) + SQUARE_BRACKET_RIGHT;
//    }

    public static String getString(User user) {
        if (user == null) return "null";
        ToStringBuilder builder = new ToStringBuilder(user, TO_STRING_STYLE);
        builder.append("id", user.getId());
        builder.append("username", user.getUsername());
        return builder.toString();
    }

    private static XmlOptions createDefaultXmlOptions() {
        XmlOptions options = new XmlOptions().setSaveAggressiveNamespaces();
        options.setSaveImplicitNamespaces(createImplicitNamespaces());
        return options;
    }

    private static Map<String, String> createImplicitNamespaces() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("air", "http://air.faregate.flitech.net");
        map.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        map.put("com", "http://schemas.navitaire.com/WebServices/DataContracts/Common");
        map.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        map.put("far", "http://faregate.flitech.net");
        map.put("pnr", "http://pnrbf.webservices.galileo.com");
        map.put("fqsbb", "http://fqsbb.webservices.galileo.com");
        map.put("fqs", "http://fqs.webservices.galileo.com");
        map.put("fqfs", "http://fqfs.webservices.galileo.com");
        map.put("fqcs", "http://fqcs.webservices.galileo.com");
        return map;
    }


    public static String getString(Object object) {
        if (object instanceof JAXBElement) {
            return getString((JAXBElement) object);
        }
        if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (!list.isEmpty() && list.get(0) instanceof XmlObject) {
                return getString(list);
            }
        }
        if (isFaregateObject(object)) {
            return ToStringBuilder.reflectionToString(object, TO_STRING_STYLE);
        }
        if (object == null) {
            return NULL_STRING;
        }
        return object.toString();
    }

    public static String getString(List<? extends JAXBElement> list) {
        StringBuilder builder = new StringBuilder(SQUARE_BRACKET_LEFT);
        for (Iterator<? extends JAXBElement> iterator = list.iterator(); iterator.hasNext();) {
            builder.append(getString(iterator.next()));
            if (iterator.hasNext()) builder.append(LIST_SEPARATOR);
        }
        builder.append(SQUARE_BRACKET_RIGHT);
        return builder.toString();
    }


//    public static String getString(Object object) {
//        if (object instanceof XmlObject) {
//            return getString((XmlObject) object);
//        }
//        if (object instanceof List) {
//            List<?> list = (List<?>) object;
//            if (!list.isEmpty() && list.get(0) instanceof XmlObject) {
//                return getString(list);
//            }
//        }
//        if (isFaregateObject(object)) {
//            return ToStringBuilder.reflectionToString(object, TO_STRING_STYLE);
//        }
//        if (object == null) {
//            return NULL_STRING;
//        }
//        return object.toString();
//    }
//
//    public static String getString(List<? extends XmlObject> list) {
//        StringBuilder builder = new StringBuilder(SQUARE_BRACKET_LEFT);
//        for (Iterator<? extends XmlObject> iterator = list.iterator(); iterator.hasNext();) {
//            builder.append(getString(iterator.next()));
//            if (iterator.hasNext()) builder.append(LIST_SEPARATOR);
//        }
//        builder.append(SQUARE_BRACKET_RIGHT);
//        return builder.toString();
//    }

    public static String getString(Object[] object) {
        return new ToStringBuilder(object, TO_STRING_STYLE).append(object).toString();
    }

    public static String getString(JAXBElement[] array) {
        return getString(Arrays.asList(array));
    }

//    public static String getString(XmlObject[] array) {
//        return getString(Arrays.asList(array));
//    }

    // TODO do we need a getString(Collection<XmlObject>)?
    public static String getString(Collection<? extends Object> list) {
        StringBuilder buffer = new StringBuilder(SQUARE_BRACKET_LEFT);
        for (Iterator<? extends Object> iterator = list.iterator(); iterator.hasNext();) {
            buffer.append(getString(iterator.next()));
            if (iterator.hasNext()) {
                buffer.append(LIST_SEPARATOR);
            }
        }
        buffer.append(SQUARE_BRACKET_RIGHT);
        return buffer.toString();
    }

    /**
     * Obfuscates the password string so passwords are not printed in the logs.
     *
     * @param password The password.
     * @return The obfuscated password, or <null> if null.
     */
    public static String getPasswordString(String password) {
        return password == null ? NULL_STRING : "****";
    }

    public static String createMessageWithId(long requestId, String message) {
        return new StringBuilder("(requestId:").append(requestId).append(") ").append(message).append(": ").toString();
    }

    public static void logJaxbObject(Logger log, Level level, JAXBElement object, long requestId, String message) {
        StringWriter sw = new StringWriter();
        sw.write(createMessageWithId(requestId, message));
        sw.write("\n");
        log.log(level, object.toString());
    }

//    public static void logXmlObject(Logger log, Level level, XmlObject object, long requestId, String message) {
//        try {
//            if (log.isEnabledFor(level)) {
//                StringWriter sw = new StringWriter();
//                sw.write(createMessageWithId(requestId, message));
//                sw.write("\n");
//                object.save(sw, new XmlOptions().setSavePrettyPrint());
//                log.log(level, sw.toString());
//            }
//        } catch (IOException e) {
//            log.error(e);
//        }
//    }

    /**
     * Create an id for logging
     */
    public static long createNewRequestId() {
        return System.currentTimeMillis();
    }

    /**
     * Logs entry to the given tracePoint to the given Logger.
     *
     * @param tracePoint The class and method being entered.
     * @param log        The logger to log to.
     * @return the time in milliseconds, to be used for timings
     */
    public static long logEntry(final String tracePoint, final Logger log) {
        long time = 0;
        if (log.isInfoEnabled()) {
            log.info(tracePoint + ": entering");
            time = System.currentTimeMillis();
        }
        return time;
    }

    /**
     * Logs exit from the given tracePoint and the duration since the given start time.
     *
     * @param tracePoint The class and method being exited
     * @param startTime
     * @param log
     */
    public static void logAndTimeExit(final String tracePoint, long startTime, final Logger log) {
        if (log.isInfoEnabled()) {
            log.info(tracePoint + ": exiting: " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }

    public static String getShortString(List<AirlineDetails> airlines) {
        if (airlines == null) return NULL;
        String result = "";
        for (int i = 0; i < airlines.size(); i++) {
            AirlineDetails airline = airlines.get(i);
            // bug 6677, call to AxisUtil.isNull() throws NoClassDefFoundError, hacking around this :-(
            if (airline == null) {
                result += NULL;
            } else {
                result += getNullSafe(airline.getAirlineCode());
            }
            if (i + 1 < airlines.size()) result += ",";
        }
        return SQUARE_BRACKET_LEFT + result + SQUARE_BRACKET_RIGHT;
    }

    private static String getNullSafe(String value) {
        return value == null ? NULL : value;
    }

//    public static String createLogInfo(MultiSubmitXmlDocument doc) {
//        String logInfo = BLANK;
//        if (doc != null) {
//            StringBuilder sb = new StringBuilder();
//            String namespaceUriFqs = "http://fqsbb.webservices.galileo.com";
//            String namespaceAliasFqs = "fqs";
//            String namespaceDeclarationFqs = "declare namespace " + namespaceAliasFqs + "='" + namespaceUriFqs + "'; ";
//            String pathAirAvailMods = ".//" + namespaceAliasFqs + ":AirAvailMods";
//            String pathStartPt = ".//" + namespaceAliasFqs + ":StartPt";
//            String pathEndPt = ".//" + namespaceAliasFqs + ":EndPt";
//            String pathStartDt = ".//" + namespaceAliasFqs + ":StartDt";
//            XmlObject[] airAvailMods = doc.selectPath(namespaceDeclarationFqs + pathAirAvailMods);
//            if (!isNullOrEmpty(airAvailMods)) {
//                XmlObject departMods = airAvailMods[0];
//                String startPtStr = findAsXML(departMods.selectPath(namespaceDeclarationFqs + pathStartPt), 0);
//                String endPtStr = findAsXML(departMods.selectPath(namespaceDeclarationFqs + pathEndPt), 0);
//                String depDateStr = findAsXML(departMods.selectPath(namespaceDeclarationFqs + pathStartDt), 0);
//                // return
//                String retDateStr = BLANK;
//                if (airAvailMods.length > 1) {
//                    XmlObject returnMods = airAvailMods[1];
//                    retDateStr = findAsXML(returnMods.selectPath(namespaceDeclarationFqs + pathStartDt), 0);
//                }
//                sb.append(startPtStr);
//                sb.append(DASH);
//                sb.append(endPtStr);
//                sb.append(SPACE);
//                sb.append(depDateStr);
//                sb.append(DASH);
//                sb.append(retDateStr);
//            }
//            logInfo = sb.toString().trim();
//        }
//        return logInfo;
//    }

//    private static String findAsXML(XmlObject[] xmlObjects, int index) {
//        String xml = BLANK;
//        if (!isNullOrEmpty(xmlObjects)) {
//            xml = xmlObjects[index].xmlText();
//        }
//        return AxisUtil.removeXmlFragments(xml);
//    }

    @SuppressWarnings("serial")
    private static final class FaregateToStringStyle extends StandardToStringStyle {

        public FaregateToStringStyle() {
            setUseShortClassName(true);
            setDefaultFullDetail(true);
            setUseIdentityHashCode(false);
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetails) {
            if (fieldName != null && !fieldName.startsWith(UNDERSCORES)) {
                super.append(buffer, fieldName, value, fullDetails);
            }
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, boolean value) {
            if (fieldName != null && !fieldName.startsWith(UNDERSCORES)) {
                super.append(buffer, fieldName, value);
            }
        }

        @Override
        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof XmlObject) {
                buffer.append(getString((XmlObject) value));
            } else if (isFaregateObject(value)) {
                buffer.append(getString(value));
            } else if (value instanceof Collection) {
                appendDetail(buffer, fieldName, (Collection<?>) value);
            } else {
                super.appendDetail(buffer, fieldName, value);
            }
        }

        // TODO: does not seem to handle ArrayLists
        @Override
        protected void appendDetail(StringBuffer buffer, String fieldName,
                                    @SuppressWarnings("rawtypes") Collection collection) {
            buffer.append(SQUARE_BRACKET_LEFT);
            for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
                appendDetail(buffer, fieldName, iterator.next());
                if (iterator.hasNext()) {
                    buffer.append(LIST_SEPARATOR);
                }
            }
            buffer.append(SQUARE_BRACKET_RIGHT);
        }
    }

    public static void logDebug(Logger logger, String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }
}

