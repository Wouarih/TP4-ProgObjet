package fr.umontpellier.iut.encheres;

import java.util.ArrayList;

public class Compte {
    private String pseudo;
    private int solde;
    private ArrayList<OffreEnchere> mesEncheres;
    private ArrayList<Produit> produitsAchetés;

    public Compte(String pseudo, int solde) {
        this.pseudo = pseudo;
        this.solde = solde;
        mesEncheres = new ArrayList<>();
        produitsAchetés = new ArrayList<>();
    }

    public int getSolde() {
        return solde;
    }

    public OffreEnchere creerOffre(Produit produit, int prix, int prixMax) {
        return null;
    }

    public int crediterCompte(int somme){
        solde = solde + somme;
        return solde;
    }
}
