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
        if(this.getSolde() >= prixMax + produit.getCoutParticipation()){
            if(prixMax >= offre.getPrixEnCours()){
                if(produit.verifierOffre(offre)){
                    this.solde = this.solde - prix - produit.getCoutParticipation();
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

    @Override
    public String toString() {
        return "Compte{" +
                "pseudo='" + pseudo + '\'' +
                ", solde=" + solde +
                ", mesEncheres=" + mesEncheres +
                ", produitsAchetés=" + produitsAchetés +
                '}';
    }
}
