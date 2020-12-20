package com.project.continental.CopService.model;


public class VehicleDetails {

    /**
     * This class is used to fetch Vehicle details from owner service
     */
    private int id;
    private String regno;
    private String owner;
    private String make;
    private String chassis;
    private String colour;
    private int cc;
    private  String fuel;
    private int weight;

    public VehicleDetails(){}
    public VehicleDetails(int id, String regno, String owner, String make, String chassis, String colour, int cc, String fuel, int weight) {
        this.id = id;
        this.regno = regno;
        this.owner = owner;
        this.make = make;
        this.chassis = chassis;
        this.colour = colour;
        this.cc = cc;
        this.fuel = fuel;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "id=" + id +
                ", regno='" + regno + '\'' +
                ", owner='" + owner + '\'' +
                ", make='" + make + '\'' +
                ", chassis='" + chassis + '\'' +
                ", colour='" + colour + '\'' +
                ", cc=" + cc +
                ", fuel='" + fuel + '\'' +
                ", weight=" + weight +
                '}';
    }
}
