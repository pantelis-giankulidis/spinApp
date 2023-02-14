package com.spinapp.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spinapp.entities.Property;
import com.spinapp.entities.QueryLog;
import com.spinapp.repositories.LogRepository;
import com.spinapp.repositories.PropertyRepository;
import com.spinapp.utils.Enumerations.Availability;
import com.spinapp.utils.Enumerations.Location;
import com.spinapp.utils.Enumerations.Type;

@Service
public class PropertyQueryService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private LogRepository logRepository;

    public Iterable<Property> getAllProperties() {
        Iterable<Property> result = propertyRepository.findAll();

        /* Persist Log */
        createAndPersistLogQuery("SELECT p FROM Property p",
                StreamSupport.stream(result.spliterator(), false).count());
        return result;
    }

    public Iterable<Property> createAndExecuteSearchQuery(List<Location> locations, List<Integer> priceRange,
            List<Integer> sizeRange,
            Availability availability, List<Type> types, Integer yearRange) {

        if (locations == null && priceRange == null && sizeRange == null && availability == null && types == null
                && yearRange == null) {
            return getAllProperties();
        }

        StringBuilder sBuilder = new StringBuilder(
                "SELECT p FROM Property p WHERE ");

        String locationString = "";
        String priceString = "";
        String sizeString = "";
        String availabilityString = "";
        String typesString = "";
        String yearsString = "";

        if (locations != null) {
            locationString = "p.location IN ('";
            for (Location l : locations) {
                locationString = locationString + l + "','";
            }
            locationString = locationString.substring(0, locationString.length() - 2) + ")";
        }

        if (priceRange != null) {
            if (priceRange.size() != 2) {
                return null;
            }
            priceString = " p.price BETWEEN " + priceRange.get(0) + " AND " + priceRange.get(1);
        }

        if (sizeRange != null) {
            if (sizeRange.size() != 2) {
                return null;
            }
            sizeString = " p.squareMeters BETWEEN " + sizeRange.get(0) + " AND " + sizeRange.get(1);
        }

        if (availability != null) {
            availabilityString = " p.availability = '" + availability + "'";
        }

        if (types != null) {
            typesString = "(";
            for (Type t : types) {
                if (t.equals(Type.APARTMENT)) {
                    typesString = typesString + " p.isApartment='true' OR ";
                }
                if (t.equals(Type.STUDIO)) {
                    typesString = typesString + " p.isStudio='true' OR ";
                }
                if (t.equals(Type.LOFT)) {
                    typesString = typesString + "p.isLoft='true' OR ";
                }
                if (t.equals(Type.MAISONETTE)) {
                    typesString = typesString + "p.isMaisonette='true' OR ";
                }
            }
            typesString = typesString.substring(0, typesString.length() - 3) + ")";
        }

        if (yearRange != null) {
            yearsString = " p.year > " + yearRange;
        }

        /* Build the query to be executed */
        sBuilder.append(locationString);
        sBuilder.append(locationString.length() < 3 || priceString.length() <= 1 ? priceString : " AND " + priceString);
        sBuilder.append((locationString.length() + priceString.length() < 4) || sizeString.length() <= 1 ? sizeString
                : " AND " + sizeString);
        sBuilder.append((locationString.length() + priceString.length() + sizeString.length() < 5)
                || availabilityString.length() <= 1 ? availabilityString
                        : " AND " + availabilityString);
        sBuilder.append(
                (locationString.length() + priceString.length() + sizeString.length() + availabilityString.length() < 6)
                        || typesString.length() <= 1
                                ? typesString
                                : " AND " + typesString);
        sBuilder.append(
                (locationString.length() + priceString.length() + sizeString.length() + availabilityString.length()
                        + typesString.length() < 10) || yearsString.length() <= 1
                                ? yearsString
                                : " AND " + yearsString);

        System.out.println(sBuilder.toString());

        /* Execute the query */
        Iterable<Property> result = propertyRepository.executeSearchQuery(sBuilder.toString());

        /* Persist Log */
        createAndPersistLogQuery(sBuilder.toString(),
                StreamSupport.stream(result.spliterator(), false).count());

        return result;
    }

    public void createAndPersistLogQuery(String query, Long resultSize) {
        QueryLog qlog = new QueryLog();
        qlog.setQuery(query);
        qlog.setNumOfResults(resultSize.intValue());
        logRepository.persistLog(qlog);
    }
}
