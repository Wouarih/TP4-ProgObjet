package fr.umontpellier.iut.encheres;
import java.util.ArrayList;
import java.util.Objects;

public class Produit {
    private final int numero;
    private String description;
    private final int prixInitial;
    private static int pasEnchere;
    private int coutParticipation;
    private ArrayList<OffreEnchere> listeOffresEmises = new ArrayList<OffreEnchere>();
    private OffreEnchere offreGagnante;
    private boolean disponible;



    public Produit(int numero, String description, int prixInitial, int coutParticipation) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.coutParticipation = coutParticipation;
        this.numero = numero;
        disponible = false;
    }

    public int getNumero() {
        return numero;
    }

    // question 1 : afin que le pas d'enchère soit le même pour tous les produits, il faut déclarer
    //l'attribut pasEnchere en static. En faisant cela nous sommes obligés de passer les méthodes
    //setPasEnchere et getPasEnchere en static aussi sinon elles ne peuvent pas manipuler pasEnchere.
    public static int getPasEnchere() {
        return pasEnchere;
    }


    public static void setPasEnchere(int pas) {
        pasEnchere = pas;
    }



    public void demarrerEnchere() {
        disponible = true;
    }

    public void arreterEnchere() {
        if(!listeOffresEmises.isEmpty()) {
            offreGagnante.getMonCompte().crediterCompte(offreGagnante.getPrixMax() + this.getCoutParticipation());
            offreGagnante.getMonCompte().getProduitsAchetés().add(this);
        }
        disponible = false;
    }

    // question 5
    public int getPrixEnCours() {
        if (listeOffresEmises.isEmpty()) {
            return prixInitial;
        }
        return offreGagnante.getPrixEnCours();
    }

    // pré-requis : l'offre passée en paramètre est valide
    public void ajouterOffre(OffreEnchere o) {

        if(listeOffresEmises.isEmpty()) {
            o.setEtatGagnant(true);
            listeOffresEmises.add(o);
            offreGagnante = o;
        }

        if(offreGagnante.getPrixMax() >= o.getPrixMax()){
                offreGagnante.setPrixEnCours(o.getPrixMax());
            }

        else if(offreGagnante.getPrixMax()<o.getPrixMax()){
                o.setEtatGagnant(true);
                o.setPrixEnCours(offreGagnante.getPrixMax());
            }
        }

    public int getCoutParticipation() {
        return coutParticipation;
    }

    public OffreEnchere getOffreGagnante() {
        if(!listeOffresEmises.isEmpty()){
            return null;
        }
        return offreGagnante;
    }

    public boolean estDisponible() {
        return disponible;
    }

    // vérifie si l'offre est correcte
    public boolean verifierOffre(OffreEnchere offre) {
        boolean verification = false;
        if (this.estDisponible() && offre.getProduit() == this) {
            if (listeOffresEmises.isEmpty() && offre.getPrixEnCours() >= this.getPrixEnCours()) {
                verification = true;
            }
            else if(!listeOffresEmises.isEmpty() && offre.getPrixEnCours() >= this.getPrixEnCours() + pasEnchere){
                verification = true;
            }
        }
        return verification;
    }


        // fonction permettant la comparaison des Produits
        // cette fonction doit rester intacte
        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (!(o instanceof Produit produit)) return false;
            return getNumero() == produit.getNumero();
        }

        // fonction auxiliaire définissant le hashCode des objets de type Produit en respectant le contrat de equals(Object o)
        // cette fonction doit rester intacte
        @Override
        public int hashCode () {
            return Objects.hash(getNumero());
        }

    @Override
    public String toString() {
        return "Produit{" +
                "offreGagnante=" + offreGagnante +
                '}';
    }
}



