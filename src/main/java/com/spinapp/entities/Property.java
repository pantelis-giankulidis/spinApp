package com.spinapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Enumerated;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Column;

import com.spinapp.utils.Enumerations.*;

@Entity
@Table(name = "property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "location")
    private Location location;

    @Column(name = "price")
    private int price;

    @Column(name = "squaremeters")
    private int squareMeters;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private Availability availability;

    @Column(name = "type")
    private String[] types;

    @Column(name = "year")
    private int year;

    @Column(name = "studio")
    private boolean isStudio;

    @Column(name = "loft")
    private boolean isLoft;

    @Column(name = "apartment")
    private boolean isApartment;

    @Column(name = "maisonette")
    private boolean isMaisonette;

    private List<Type> ptypes;

    Property() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String[] getTypes() {
        /*
         * String[] pTypes = types.substring(1, types[0].length() - 1).split(",");
         * List<Type> propertyTypes = new ArrayList<Type>();
         * for (int i = 0; i < pTypes.length; i++) {
         * propertyTypes.add(Type.valueOf(pTypes[i]));
         * }
         * this.ptypes = propertyTypes;
         */
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Type> getPtypes() {
        /*
         * String[] pTypes = types.substring(1, types.length() - 1).split(",");
         * List<Type> propertyTypes = new ArrayList<Type>();
         * for (int i = 0; i < pTypes.length; i++) {
         * propertyTypes.add(Type.valueOf(pTypes[i]));
         * }
         * this.ptypes = propertyTypes;
         */
        return ptypes;
    }

    public void setPtypes(List<Type> ptypes) {
        this.ptypes = ptypes;
    }

    public boolean isStudio() {
        return isStudio;
    }

    public void setStudio(boolean isStudio) {
        this.isStudio = isStudio;
    }

    public boolean isLoft() {
        return isLoft;
    }

    public void setLoft(boolean isLoft) {
        this.isLoft = isLoft;
    }

    public boolean isApartment() {
        return isApartment;
    }

    public void setApartment(boolean isApartment) {
        this.isApartment = isApartment;
    }

    public boolean isMaisonette() {
        return isMaisonette;
    }

    public void setMaisonette(boolean isMaisonette) {
        this.isMaisonette = isMaisonette;
    }

}
