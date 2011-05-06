package net.flitech.faregate.service.impl;

import static net.flitech.ParamChecker.checkNotNull;
import static net.flitech.ParamChecker.checkNotNullOrEmpty;
import static net.flitech.ParamChecker.isNullOrEmpty;
import static com.nz.travel.mentor.enums.Countries.NZ;
import static net.flitech.faregate.model.impl.RouteType.DOMESTIC;
import static net.flitech.faregate.model.impl.RouteType.INTERNATIONAL;
import static net.flitech.faregate.model.impl.RouteType.SWP;
import static net.flitech.faregate.model.impl.RouteType.TRANSTASMAN;

import java.util.List;

import javax.annotation.Resource;

import com.googlecode.ehcache.annotations.Cacheable;

import com.nz.travel.mentor.model.impl.Country;
import net.sf.ehcache.Ehcache;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.flitech.faregate.service.LocationGuide;

/**
 * Business methods for locations (countries, cities, airports).
 */
@Service("locationGuide")
public class LocationGuideImpl extends FareGateObject implements LocationGuide {

    private static final String QUOTE = "'";

    @Resource(name="locationDAO")
    private LocationDAO locationDAO;

    public LocationGuideImpl() {
        super();
    }

    public LocationGuideImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    /**
     * Uses an {@link Ehcache} to cache the {@link Country} for the city.
     */
    @Cacheable(cacheName = "countriesByCityCode")
    @Override
    public Country findCountryByAirportOrCityCode(String airportOrCityCode) {
        checkNotNullOrEmpty("airportOrCityCode", airportOrCityCode);
        try {
            return locationDAO.getCountryByAirportOrCityCode(airportOrCityCode);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Cannot find country for airport or city code '"
                    + airportOrCityCode + QUOTE, e.getExpectedSize());
        }
    }

    @Cacheable(cacheName = "countriesByCountryCode")
    @Override
    public Country findCountryByCountryCode(String countryCode) {
        checkNotNullOrEmpty("countryCode", countryCode);
        try {
            return locationDAO.findCountry(countryCode);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Cannot find country for code '" + countryCode + QUOTE,
                    e.getExpectedSize());
        }
    }

    /** Doesn't need to be cached since the countries came from somewhere else. */
    @Override
    public boolean isDomestic(Country departureCountry, Country arrivalCountry) {
        checkNotNull("departureCountry", departureCountry);
        checkNotNull("arrivalCountry", arrivalCountry);
        return departureCountry.equals(arrivalCountry);
    }

    /** Separate cache since it doesn't go through the findCountryByAirportOrCityCode() cache. */
    @Cacheable(cacheName = "isDomesticByCityCodes")
    @Override
    public boolean isDomestic(String departureCity, String arrivalCity) {
        Country departureCountry = findCountryByAirportOrCityCode(departureCity);
        Country arrivalCountry = findCountryByAirportOrCityCode(arrivalCity);
        return isDomestic(departureCountry, arrivalCountry);
    }

    @Cacheable(cacheName = "findRouteTypeByCityCodes")
    public RouteType findRouteType(String departureCity, String arrivalCity) {
        Country departureCountry = findCountryByAirportOrCityCode(departureCity);
        Country arrivalCountry = findCountryByAirportOrCityCode(arrivalCity);
        if (isDomestic(departureCountry, arrivalCountry)) return DOMESTIC;
        if (isRouteSWP(departureCountry, arrivalCountry)) return SWP;
        if (isTransTasman(departureCountry, arrivalCountry)) return TRANSTASMAN;
        return INTERNATIONAL;
    }

    /** Must be different countries and one of them must be NZ. */
    boolean isTransTasman(Country departureCountry, Country arrivalCountry) {
        if (departureCountry == null || arrivalCountry == null) return false;
        if (isNullOrEmpty(departureCountry.getCode())) return false;
        if (isNullOrEmpty(arrivalCountry.getCode())) return false;
        if (departureCountry.getCode().equals(arrivalCountry.getCode())) return false;
        return isNZ(departureCountry) || isNZ(arrivalCountry);
    }

    private boolean isNZ(Country country) {
        return country != null && !isNullOrEmpty(country.getCode()) && NZ.toString().equals(country.getCode());
    }

    /** Must be different countries and different regions and one of them must be SWP, but they can't both be SWP. */
    boolean isRouteSWP(Country departureCountry, Country arrivalCountry) {
        if (departureCountry == null || arrivalCountry == null) return false;
        if (isNullOrEmpty(departureCountry.getCode())) return false;
        if (isNullOrEmpty(arrivalCountry.getCode())) return false;
        if (departureCountry.getCode().equals(arrivalCountry.getCode())) return false;
        return isRegionSWP(departureCountry) ^ isRegionSWP(arrivalCountry);
    }

    private boolean isRegionSWP(Country country) {
        return country != null && country.getRegion() != null && SWP.getCode().equals(country.getRegion().getCode());
    }

    @Override
    @Cacheable(cacheName = "citiesByAirportOrCityCode")
    public City findCity(String airportOrCityCode) {
        try {
            return locationDAO.getCityByAirportOrCityCode(airportOrCityCode);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Cannot find city for code '" + airportOrCityCode + QUOTE,
                    e.getExpectedSize());
        }
    }

    @Cacheable(cacheName = "airportsByAirportCode")
    @Override
    public Airport findAirport(String airportCode) {
        try {
            return locationDAO.findAirport(airportCode);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Cannot find airport for code '" + airportCode + QUOTE,
                    e.getExpectedSize());
        }
    }

    @Override
    public List<Airport> getAllAirports() {
        return locationDAO.findAllAirports();
    }
}

