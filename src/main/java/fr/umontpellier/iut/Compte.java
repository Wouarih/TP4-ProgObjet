package fr.umontpellier.iut;

import java.util.ArrayList;

public class Compte {
    private String pseudo;
    private double solde;
    private ArrayList<OffreEnchere> mesEncheres;
    private ArrayList<Produit> produitsAchetés;

    public Compte(String pseudo, double solde) {
        this.pseudo = pseudo;
        this.solde = solde;
        mesEncheres = new ArrayList<>();
        produitsAchetés = new ArrayList<>();
    }

    public double getSolde() {
        return solde;
    }

    public OffreEnchere creerOffre(Produit produit, double prix, double prixMax) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }
}
