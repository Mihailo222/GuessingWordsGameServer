/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ServerskaForma;
import java.util.ArrayList;
import model.Slovo;
import operacije.Operacije;
import server.ObradaKlijentskogZahteva;

/**
 *
 * @author Acer
 */
public class Kontroler {
    private static  Kontroler instance;

    
    private ArrayList<String> reci = new ArrayList<>();
    private String izabranaRec;
    private ArrayList<Slovo> izabranaSlova=new ArrayList<>();
    //***************************************************
    private int brojPogodaka=0;
    private ServerskaForma sf;

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public int getBrojPogodaka() {
        return brojPogodaka;
    }

    public void setBrojPogodaka(int brojPogodaka) {
        this.brojPogodaka = brojPogodaka;
    }
    
    //***************************************************
    

    
    
    
 //   private ArrayList<Slovo> rez=new ArrayList<>();   NI SLUCAJNO OVDE INICIJALIZOVATI OVU LSTU, jer ti onda svakim klikom reinicijalizuje i dodaje na postojecu rec opet istu 
    
    //***************************************
    
    private ArrayList<ObradaKlijentskogZahteva> listaKlijenata = new ArrayList<>(); // lista klijenata gde svaki ima svoj redni broj

    public ArrayList<ObradaKlijentskogZahteva> getListaKlijenata() {
        return listaKlijenata;
    }

    public void setListaKlijenata(ArrayList<ObradaKlijentskogZahteva> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }
    
    
    //******************************************
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Kontroler() {
        reci.add("MARKO");
        reci.add("MODEL");
        reci.add("AAAAA");
        reci.add("PORIV");
        
        
        
        
        
        
        
    }
    
    
    public static Kontroler getInstance(){
        if(instance==null)
            instance=new Kontroler();
        
        return instance;
    }

    public ArrayList<String> getReci() {
        return reci;
    }

    public void setReci(ArrayList<String> reci) {
        this.reci = reci;
    }

    public String getIzabranaRec() {
        return izabranaRec;
    }

    
    
    
    
    
    
    
    public void setIzabranaRec(String izabranaRec) { //marko
        this.izabranaRec = izabranaRec;
    }

    public ArrayList<Slovo> vratiSlova(char c) {

         String poruka="";
        ArrayList<Slovo> rez = new ArrayList<>();


        for(Slovo sl : izabranaSlova){ //marko 
            if(c==sl.getSlovo()){ //a
                rez.add(sl);
                brojPogodaka++;
            }
     
                
            }
            
           
            
            if(brojPogodaka==5){
                if(listaKlijenata.get(0).getBrojPogodjenih()>listaKlijenata.get(1).getBrojPogodjenih())
                    poruka="POBEDIO JE IGRAC BROJ 1!";
                
                if(listaKlijenata.get(0).getBrojPogodjenih()<listaKlijenata.get(1).getBrojPogodjenih())
                       poruka="POBEDIO JE IGRAC BROJ 2!";
                
           
            
            
                  
            for(ObradaKlijentskogZahteva okz:listaKlijenata){
                
             
                okz.obavestiKlijenta(Operacije.KRAJ, poruka);
                 
            }
            
            
            sf.prikaziPorukuPobede(poruka);
            
            
            
            
            }
           
            if(listaKlijenata.get(0).getBrojPokusaja()<=0 && listaKlijenata.get(1).getBrojPokusaja()<=0){
                
                poruka="POBEDIO JE KOMPJUTER!";
                
                for(ObradaKlijentskogZahteva okz:listaKlijenata){
                    okz.obavestiKlijenta(Operacije.KRAJ, poruka);
                }
                
                
            }
            
            
            
      
            
        
            
            
            
        


        System.out.println("SLOVA koja vracam:");
         for(Slovo sl1:rez){
            System.out.println(sl1);
         }


        return rez;





    }

    public void popuniListuIzabranaSlova(String izabranaR) {


        for(int i=0; i<izabranaR.length(); i++){
            Slovo s=new Slovo(izabranaR.charAt(i), i);
            izabranaSlova.add(s);
        }




    }

    public void updateLabeleServer() {


        sf.updateLabeleServer();

    }

  
 



    
    
    
   
}
