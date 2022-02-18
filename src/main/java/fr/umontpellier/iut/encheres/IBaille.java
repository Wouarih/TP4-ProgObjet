package fr.umontpellier.iut.encheres;
import java.util.Scanner;

public class IBaille {

    public static void main(String[] args) {

        Compte c1 = new Compte("Wouarih", 10000);
        Compte c2 = new Compte("Billy", 15000);
        Compte c3 = new Compte("Elhou", 12250);

        Produit p1 = new Produit(1, "Tableau de Picasso", 1000, 100);
        Produit p2 = new Produit(2, "Elfe de maison", 2000, 100);
        Produit p3 = new Produit(3, "GameBoy Advance SP Tribal", 100, 10);

        Scanner enchere = new Scanner(System.in);
        System.out.println("Début des enchères");



    }
}
