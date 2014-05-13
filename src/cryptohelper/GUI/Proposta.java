/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryptohelper.GUI;

import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.UserInfo;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
public class Proposta {
    private UserInfo proponente;
    private UserInfo partner;
    private SistemaCifratura sdc;
    private boolean stato;

    public Proposta(UserInfo utilizzatoreSistema, UserInfo partner, SistemaCifratura sdc) {
        this.proponente = utilizzatoreSistema;
        this.partner = partner;
        this.sdc = sdc;
    }
    
    //TO-DO
    public boolean save(){
        return false;
    }

    public UserInfo getProponente() {
        return proponente;
    }

    public void setProponente(UserInfo proponente) {
        this.proponente = proponente;
    }

    public UserInfo getPartner() {
        return partner;
    }

    public void setPartner(UserInfo partner) {
        this.partner = partner;
    }

    public SistemaCifratura getSdc() {
        return sdc;
    }

    public void setSdc(SistemaCifratura sdc) {
        this.sdc = sdc;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    
    
    
    
}
