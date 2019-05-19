/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author KrystofViktora
 */
public class Dvd {
   
    public String adSoyad;
    public String DogumTarihi;
    private ArrayList<String> songs;

    public Dvd(String adSoyad, String DogumTarihi) {
        this.adSoyad = adSoyad;
        this.DogumTarihi = DogumTarihi;
        this.songs = new ArrayList<String>();
    }

    public Dvd(String adSoyad, String DogumTarihi, ArrayList<String> songs) {
        this.adSoyad = adSoyad;
        this.DogumTarihi = DogumTarihi;
        this.songs = songs;
    }

    public Dvd() {
        super();
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getDogumTarihi() {
        return DogumTarihi;
    }

    public void setDogumTarihi(String DogumTarihi) {
        this.DogumTarihi = DogumTarihi;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return adSoyad + " - " + DogumTarihi ;
    }
    
    public int compareWith(Dvd dvd) {
         Locale trlocale= new Locale("tr-TR");
        // A is before B
        if(this.adSoyad.toLowerCase(trlocale).compareTo(dvd.adSoyad.toLowerCase(trlocale)) < 0) {
            return 0; 
        }
        
        else {
            return 1;
        }
    }
    
    public void removeSong(int index) {
        this.songs.remove(index);
    }
}
