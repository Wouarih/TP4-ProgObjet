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

    public int getPrixEnCours() {
        return prixEnCours;
    }

    public int getPrixMax() {
        return prixMax;
    }

    public void setEtatGagnant(boolean etat) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
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