package fr.umontpellier.iut.encheres;
import java.util.ArrayList;
import java.util.Objects;

public class Produit {
    private final int numero;
    private String description;
    private final int prixInitial;
    private static int pasEnchere;
    private int coutParticipation;
    private ArrayList<Integer> listeOffresEmises = new ArrayList<Integer>();
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
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public int getCoutParticipation() {
        return coutParticipation;
    }

    public OffreEnchere getOffreGagnante() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public boolean estDisponible() {
        return disponible;
    }

    // vérifie si l'offre est correcte
    public boolean verifierOffre(OffreEnchere offre) {
        boolean verification = false;
        if (this.disponible && offre.getProduit() == this) {
            if (offre.getPrixEnCours() == this.prixInitial) {

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
    }

