package com.spinapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spinapp.entities.Property;
import com.spinapp.services.PropertyQueryService;
import com.spinapp.utils.Enumerations.Availability;
import com.spinapp.utils.Enumerations.Location;
import com.spinapp.utils.Enumerations.Type;

@RestController
@RequestMapping("/spinapp")
public class MainController {

    @Autowired
    private PropertyQueryService propertyQueryService;

    @GetMapping("/query")
    public Iterable<Property> applyQuery(@RequestParam(name = "location", required = false) List<Location> locations,
            @RequestParam(name = "priceRange", required = false) List<Integer> priceRange,
            @RequestParam(name = "sizeRange", required = false) List<Integer> sizeRange,
            @RequestParam(name = "availability", required = false) Availability availability,
            @RequestParam(name = "type", required = false) List<Type> types,
            @RequestParam(name = "year", required = false) Integer year) {

        return propertyQueryService.createAndExecuteSearchQuery(locations,
                priceRange, sizeRange, availability, types,
                year);

    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Property> getAll() {
        return propertyQueryService.getAllProperties();
    }
}
