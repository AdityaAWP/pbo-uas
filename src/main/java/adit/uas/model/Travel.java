package adit.uas.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Travel {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty origin;
    private final SimpleStringProperty destination;
    private final SimpleStringProperty schedule;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty jumlahTiket;

    public Travel(int id, String origin, String destination, String schedule, double price, int jumlahTiket) {
        this.id = new SimpleIntegerProperty(id);
        this.origin = new SimpleStringProperty(origin);
        this.destination = new SimpleStringProperty(destination);
        this.schedule = new SimpleStringProperty(schedule);
        this.price = new SimpleDoubleProperty(price);
        this.jumlahTiket = new SimpleIntegerProperty(jumlahTiket);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOrigin() {
        return origin.get();
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public String getDestination() {
        return destination.get();
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getSchedule() {
        return schedule.get();
    }

    public void setSchedule(String schedule) {
        this.schedule.set(schedule);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getJumlahTiket() {
        return jumlahTiket.get();
    }

    public void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket.set(jumlahTiket);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty originProperty() {
        return origin;
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public SimpleStringProperty scheduleProperty() {
        return schedule;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public SimpleIntegerProperty jumlahTiketProperty() {
        return jumlahTiket;
    }

    @Override
    public String toString() {
        return origin.get() + " to " + destination.get() + " - " + schedule.get();
    }
}
