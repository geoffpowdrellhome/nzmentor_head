package com.nz.travel.mentor.util;

import net.flitech.fares.GetFaresRequest;
import net.flitech.fares.RulesRequest;

import net.flitech.faregate.air.Booking;
import net.flitech.faregate.air.Enquiry;
import net.flitech.faregate.air.RetrieveBookingsRequest;
import net.flitech.faregate.air.ApprovedRulesRequest;

/**
 * Utility class related to exceptions and their contents.
 * @author Ben Warren
 */
public final class ExceptionUtil {

    private ExceptionUtil() {}

    public static String createBookingMessage(Booking booking, String message) {
        return message + " Booking details: " + LogUtil.getString(booking);
    }

    public static String createEnquiryMessage(Enquiry enquiry, String message) {
        return message + " Enquiry details: " + LogUtil.getString(enquiry);
    }

    public static String createRulesRequestMessage(RulesRequest request, String message) {
        return message + " RulesRequest details: " + LogUtil.getString(request);
    }

    public static String createApprovedRulesRequestMessage(ApprovedRulesRequest request, String message) {
        return message + " ApprovedRulesRequest details: " + LogUtil.getString(request);
    }

    public static String createRetrieveBookingsMessage(RetrieveBookingsRequest details, String message) {
        return message + " RetrieveBookings details: " + LogUtil.getString(details);
    }

    public static String createGetFaresRequestMessage(GetFaresRequest request, String message) {
        return message + " GetFaresRequest details: " + LogUtil.getString(request);
    }
}
