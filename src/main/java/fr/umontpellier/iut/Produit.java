package fr.umontpellier.iut;

public class Produit {
    private int numProduit;
    private String descriptionProduit;
    private double prixCourant;
    private double pasEnchere;
    private double coutParticipation;

    private boolean disponible;

    public Produit(int numProduit, String descriptionProduit, double prixCourant, double coutParticipation) {
        this.descriptionProduit = descriptionProduit;
        this.prixCourant = prixCourant;
        this.coutParticipation = coutParticipation;
        this.numProduit = numProduit;
        disponible = false;
    }

    public int getNumProduit() {
        return numProduit;
    }

    // question 1
    public void setPasEnchere(double pas) {
        pasEnchere = pas;
    }

    public void demarrerEnchere() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public void arreterEnchere() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    // pré-requis : l'offre passée en paramètre est valide
    public void ajouterOffre(OffreEnchere o) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public double getCoutParticipation() {
        return coutParticipation;
    }

    public OffreEnchere getOffreGagnante() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public boolean estDisponible() {
        return disponible;
    }

    // vérifie si l'offre est valide
    public boolean verifierOffre(OffreEnchere offre) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }
}
