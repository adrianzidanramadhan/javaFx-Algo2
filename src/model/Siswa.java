package model;

import javafx.beans.property.*;

public class Siswa {
    private StringProperty nama;
    private StringProperty nim;
    private DoubleProperty nilai1, nilai2, nilai3;

    public Siswa(String nama, String nim, double nilai1, double nilai2, double nilai3) {
        this.nama = new SimpleStringProperty(nama);
        this.nim = new SimpleStringProperty(nim);
        this.nilai1 = new SimpleDoubleProperty(nilai1);
        this.nilai2 = new SimpleDoubleProperty(nilai2);
        this.nilai3 = new SimpleDoubleProperty(nilai3);
    }

    public String getNama() { return nama.get(); }
    public StringProperty namaProperty() { return nama; }

    public String getNim() { return nim.get(); }
    public StringProperty nimProperty() { return nim; }

    public double getNilai1() { return nilai1.get(); }
    public DoubleProperty nilai1Property() { return nilai1; }

    public double getNilai2() { return nilai2.get(); }
    public DoubleProperty nilai2Property() { return nilai2; }

    public double getNilai3() { return nilai3.get(); }
    public DoubleProperty nilai3Property() { return nilai3; }

    public double getRataRata() {
        return (getNilai1() + getNilai2() + getNilai3()) / 3;
    }

    public ReadOnlyDoubleWrapper rataRataProperty() {
        return new ReadOnlyDoubleWrapper(getRataRata());
    }
}
