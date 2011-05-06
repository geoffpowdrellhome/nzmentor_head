package com.nz.travel.mentor.util;

import com.nz.travel.mentor.model.impl.User;
import net.flitech.faregate.ValidationException;
import net.flitech.faregate.air.Booking;

public interface TimeZoneConverter {

    String convertArrivalToDeparture(User user, Booking booking) throws ValidationException;

    String convertReturnDepartureToArrival(User user, Booking booking) throws ValidationException;

    String convertReturnArrivalToReturnDeparture(User user, Booking booking) throws ValidationException;
}
