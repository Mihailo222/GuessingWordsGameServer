/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Kontroler;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slovo;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class ObradaKlijentskogZahteva extends Thread{

    Socket socket;
    private int redniBrojKlijenta;
    private int brojPogodjenih=0;
    private int brojPokusaja=10;
    
    
    

    public ObradaKlijentskogZahteva(Socket socket, int redniBrojKlijenta) {
        this.socket = socket;
        this.redniBrojKlijenta=redniBrojKlijenta;
    }
    
    
    
    
    
    
    
    @Override
    public void run() {
        
        while(true){
            KlijentskiZahtev kz = primiZahtev();
            System.out.println((char)kz.getParam());
            
            switch(kz.getOperacija()){
                case Operacije.IGRAJ:
                    ServerskiOdgovor so = new ServerskiOdgovor();
                    ArrayList<Slovo> slovaRez=Kontroler.getInstance().vratiSlova((char)kz.getParam());
                    so.setOdgovor(slovaRez);
                    so.setOperacija(Operacije.IGRAJ);
                   
                    for(ObradaKlijentskogZahteva okz :Kontroler.getInstance().getListaKlijenata())
                                okz.posaljiOdgovor(so);
                    
                    
                    brojPokusaja--;
                    
                    brojPogodjenih=brojPogodjenih+slovaRez.size();
                    System.out.println("POGODJENIH: "+brojPogodjenih);
                    System.out.println("POKUSAJA: "+brojPokusaja);
                    
                    
                    Kontroler.getInstance().updateLabeleServer();
                   
                    
                    
                    
                      break;
                
                
          
                
            }
            
            
            
        }
        
        
        
        
        
    }

    public KlijentskiZahtev primiZahtev() {

        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }


return null;


    }

    public void posaljiOdgovor(ServerskiOdgovor so) {


        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        
            oos.writeObject(so);
            oos.flush();
        
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }




    }
    
    
    public void obavestiKlijenta(int operacija, Object param){
        
        ServerskiOdgovor so = new ServerskiOdgovor();
        so.setOdgovor(param);
        so.setOperacija(operacija);
        posaljiOdgovor(so);
        
    }

    //*************************************************************
    
    public int getRedniBrojKlijenta() {
        return redniBrojKlijenta;
    }

    public void setRedniBrojKlijenta(int redniBrojKlijenta) {
        this.redniBrojKlijenta = redniBrojKlijenta;
    }

    public int getBrojPogodjenih() {
        return brojPogodjenih;
    }

    public void setBrojPogodjenih(int brojPogodjenih) {
        this.brojPogodjenih = brojPogodjenih;
    }

    public int getBrojPokusaja() {
        return brojPokusaja;
    }

    public void setBrojPokusaja(int brojPokusaja) {
        this.brojPokusaja = brojPokusaja;
    }

  
    
    
    
    
}
