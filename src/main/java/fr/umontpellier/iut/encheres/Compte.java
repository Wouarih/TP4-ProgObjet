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

    public ArrayList<Produit> getProduitsAchetés() {
        return produitsAchetés;
    }

    public int getSolde() {
        return solde;
    }

    public OffreEnchere creerOffre(Produit produit, int prix, int prixMax) {

        OffreEnchere offre = new OffreEnchere(prix, prixMax, produit, this);
        if(this.getSolde() >= offre.getPrixMax()+ produit.getCoutParticipation()){
            if(offre.getPrixMax() >= offre.getPrixEnCours()){
                if(produit.verifierOffre(offre)){
                    this.solde = this.solde - prixMax - produit.getCoutParticipation();
                    return offre;
                }
            }
        }

        return null;
    }

    public int crediterCompte(int somme){
        solde = solde + somme;
        return solde;
    }

    public String toString() {
        return  pseudo +
                " a fait des enchères de " + mesEncheres +
                " qui lui ont permis d'acquérir : " + produitsAchetés;
    }
}
