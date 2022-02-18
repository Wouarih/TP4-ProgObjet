package fr.umontpellier.iut.encheres;

public class OffreEnchere {
    private int prixEnCours;
    private int prixMax;
    private Produit produit;
    private boolean etatGagnant;
    private Compte monCompte;

    public OffreEnchere(int prixEnCours, int prixMax, Produit produit, Compte monCompte) {
        this.prixEnCours = prixEnCours;
        this.prixMax = prixMax;
        this.produit = produit;
        etatGagnant = false;
        this.monCompte = monCompte;
    }

    public Compte getMonCompte() {
        return monCompte;
    }

    public Produit getProduit() {

        return produit;
    }

    public int getPrixEnCours() {

        return prixEnCours;
    }

    public int getPrixMax() {

        return prixMax;
    }

    public void setEtatGagnant(boolean etat) {
        etatGagnant = etat;
        if(!etat){
            monCompte.crediterCompte(produit.getPrixEnCours() + produit.getCoutParticipation());
        }
    }

    public void setPrixEnCours(int prixEnCours) {
        this.prixEnCours = prixEnCours;
    }

    @Override
    public String toString() {
        return "OffreEnchere{" +
                ", prixEnCours=" + prixEnCours +
                ", prixMax=" + prixMax +
                ", produit=" + produit.getNumero() +
                ", est gagnante  ? -> " + etatGagnant +
                '}';
    }
}
