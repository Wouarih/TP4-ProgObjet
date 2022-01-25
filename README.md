Vous êtes chargé de développer le système d'enchères **iBaille**. Pour avoir une idée globale du système,
voici le principe général de fonctionnement :

* des produits sont mis en vente avec un prix initial (le prix de base) ;
* des utilisateurs peuvent enchérir sur les produits jusqu'à ce que l'enchère soit arrêtée ;
* pour pouvoir participer les utilisateurs doivent payer un coût de participation (différent pour chaque produit) ; ce montant ne sera jamais remboursé -- bénéfice du site ;
* à la fin de la vente, l'utilisateur ayant proposé le prix le plus élevé, remporte le produit ;
* pour éviter des enchères inutiles (de 1 centime par exemple), le même pas d’enchère minimal est défini pour tous les produits vendus via **iBaille**;
* afin d'éviter aux utilisateurs de devoir encherir de nombreuses fois (lors d'un "duel" entre deux utilisateurs par exemple), lorsqu'un utilisateur propose un prix pour un produit, il propose également un prix maximal qu'il est prêt à débourser en cas d'enchère concurrente. Le détail de la détermination du gagnant et du prix final du produit seront donnés ci-dessous.

Un squelette du code vous est fourni avec quelques classes de tests unitaires. Prenez le temps de le lire et de le comprendre car vous aurez à le compléter en y ajoutant des méthodes et des attributs qui vous paraissent nécessaires. Discutez avec votre enseignant avant de démarrer le travail.

1. Quelque chose a été oublié dans la classe `Produit` : comme indiqué précédemment, le pas d'enchère doit être le même pour tous les produits mais modifiable par l'utilisateur. Modifiez la déclaration de cet attribut afin de satisfaire cette contrainte. Doit-on modifier également la méthode `void setPasEnchere()` ?

   **Remarque** : ne pas confondre la notion d'_utilisateur du logiciel_ (non-informaticien) et l'_utilisateur-programmeur_ qui est censé se servir de votre application pour poursuivre son développement, pour sa maintenance, le débuggage etc. Ici l'utilisateur c'est l'informaticien.

1. Complétez la classe `Compte` en y ajoutant une méthode qui permet de créditer le compte avec une somme donnée. Cette somme pourra éventuellement être négative, ce qui permettra alors de retirer de l'argent du compte.

1. La classe `OffreEnchere` représentera une enchère proposée par un utilisateur pour un produit donné. Certains de ses attributs et méthodes vous sont donnés. Ajoutez dans la classe `OffreEnchere` une méthode modifieur (_setter_) pour le prix en cours.

   **Remarque :** observez que dans le constructeur de cette classe, aucune vérification concernant la cohérence des attributs de l'offre créée avec ceux du produit n'a été faite (ce n'est pas la responsabilité de l'objet `OffreEnchere`).

   **Remarque :** observez également que par défaut l'offre est désignée comme perdante à travers un booléen.

1. Dans la classe `Produit`, ajoutez des attributs permettant respectivement de

    * stocker toutes les offres émises sur ce produit -- on vous conseille d'utiliser une structure de données de type liste prédéfinie en _Java_, comme `java.util.ArrayList` ou `java.util.LinkedList`, mais vous êtes libres d'utiliser d'autres solutions ;
    * stocker l'offre gagnante actuelle

1. Le prix en cours d'un produit est défini au départ (lorsque le produit n'a reçu aucune offre d'enchère) comme son prix initial. Ensuite le prix en cours va correspondre à celui de l'enchère gagnante actuelle. Implémentez la méthode `double getPrixEnCours()` de la classe `Produit` qui retourne le prix en cours du produit.

Étant donné un produit (ayant un prix en cours **c**) recevant une offre **o** (de prix courant **p<sub>o</sub>**, peu importe son prix maximal), on dit que **o** est **correcte** pour le produit si toutes les conditions suivantes sont respectées :
* le produit est ouvert aux enchères
* le produit de l'offre **o** est bien le même que le produit courant qui reçoit **o**
* si **o** est la première offre, alors il faut que **p<sub>o</sub>**  &ge; **c**
* si **o** n'est pas la première offre (dans ce cas **c** correspond au prix courant de l'offre actuellement gagnante), alors il faut que **p<sub>o</sub>  &ge; c + &delta;**


6. Implémentez la méthode `boolean verifierOffre(OffreEnchere offre)` de la classe `Produit`, qui vérifie si une offre est correcte.

Étant donné un compte (ayant un solde **s**), et une offre **o** (de prix courant **p<sub>o</sub>** et maximum **M<sub>o</sub>**)  pour un produit (de coût de participation **c<sub>p</sub>**),
on dit que **o** est **valide** si toutes les conditions suivantes sont respectées :
*  **s**  &ge; **M<sub>o</sub>** + **c<sub>p</sub>**
* **M<sub>o</sub>** &ge; **p<sub>o</sub>**
* **o** est une offre correcte pour le produit

7. Écrivez le code de la méthode `public OffreEnchere creerOffre(Produit produit, double prixCourant, double prixMax)` de la classe `Compte` qui, à partir de ses paramètres, instancie et retourne une offre si celle-ci est **valide** avec ces paramètres. Également, si l'offre est valide, la méthode devra débiter le compte de `prixMax` + le coût de participation de le produit. La méthode doit retourner `null` si l'offre n'est pas valide.

   **Pensez à écrire des tests unitaires (beaucoup de tests unitaires !) pour les différentes méthodes implémentées pour cette fonction...**

Passons maintenant à la gestion des coûts liés à la création d'offres. Comme vous l'avez remarqué dans la question précédente, dès qu'un compte
créé une offre valide, alors le compte est directement débité de **M<sub>o</sub>**+**c<sub>p</sub>**. L'idée derrière ce débit immédiat est de s'assurer qu'un compte
qui proposerait des offres sur de nombreux produits en parallèle aurait de quoi toutes les payer s'il était vainqueur sur tous ces produits.
Ainsi, si une offre s'avère gagnante, alors nul besoin de rembourser, mais dans le cas contraire, lorsqu'une offre est déclarée perdante, il faut rembourser **M<sub>o</sub>** sur le compte correspondant.

8. Implémentez la méthode `setEtatGagnant(boolean etat)` de la classe `OffreEnchere`. On utilisera cette méthode pour faire basculer une enchère à un état (gagnante ou perdante). Pensez à rembourser le compte en cas de passage à l'état perdant.

Nous allons maintenant implémenter la méthode la plus importante, qui va gérer la concurrence entre plusieurs offres valides pour un produit fixé.
Voici les règles permettant de déterminer si une nouvelle offre valide est gagnante ou non, et de fixer la nouvelle valeur du prix courant.

* Considérons un produit. Quand une nouvelle offre (supposée valide) **o2** (de prix courant **p<sub>o2</sub>** et maximum **M<sub>o2</sub>**) arrive pour ce produit
    * si ce n'est pas la première enchère, alors notons **p<sub>o1</sub>** et **M<sub>o1</sub>** le prix courant et maximum de l'offre gagnante actuelle.
        * si **M<sub>o1</sub>** &ge; **M<sub>o2</sub>**, alors le gagnant ne change pas et la valeur **p<sub>o</sub>** est actualisée à **M<sub>o2</sub>** ;
        * si **M<sub>o1</sub>** < **M<sub>o2</sub>**, alors la nouvelle enchère est désignée comme gagnante, et la valeur **p<sub>o</sub>** est actualisée à max **(M<sub>o1</sub>, p<sub>o2</sub>)** ;
    * si aucune enchère n'a encore été déposée sur ce produit, alors la nouvelle offre est désignée comme gagnante


On remarque qu'un utilisateur peut déposer une nouvelle offre d'enchère sur le même produit sur lequel il a déjà déposé une offre d'enchère. Par exemple, il pourra le faire si son offre a été "battue" par un autre enchérisseur.
<!--    Par définition, le gagnant est celui dont le prix courant est supérieur au prix maximal proposé par tous les autres enchérisseurs.
-->


9. Implémentez la méthode `void ajouterOffre(OffreEnchere o)` de la classe `Produit` qui, étant donnée une nouvelle offre `o` (supposée valide, et pour le même produit), effectue les actions suivantes :
    * ajoute `o` à la liste d'offres d'enchères du produit ;
    * met à jour l'offre gagnante actuelle sur le produit (en déterminant si `o` est gagnante ou non, selon les règles ci-dessus) ;
    * change l'état de l'offre gagnante (utiliser la méthode `setEtatGagnant(boolean etat)`) ;
    * et qui dans le cas où `o` n'était pas la première enchère, et donc qu'il existe une offre qui va devenir perdante, change l'état de l'offre perdante (utiliser la méthode `setEtatGagnant(boolean etat)`), déclenchant ainsi son remboursement.

   **Remarque :** nul besoin de vérifier ici si l'offre est valide, à l'utilisation de la méthode `void ajouterOffre(OffreEnchere o)` on suppose l'objet `o` comme étant valide.

   **Remarque :** vous pouvez ajouter des méthodes auxiliaires qui vous paraissent nécessaires.

   **Remarque :** écrire des tests unitaires pour cette fonction et toutes les fonctions auxiliaires est fortement conseillé.


10. Implémentez la méthode `void demarrerEnchere()` de `Produit` pour qu'elle rende l'objet disponible.
    Implémentez également la méthode réciproque `void arreterEnchere()`, qui en plus de rendre l'objet indisponible, va rembourser le compte lié à l'offre gagnante `o` de **M<sub>o</sub>** - **c**, où **c** est le prix courant de l'objet (qui correspond donc au moment de la cloture au prix auquel l'objet va partir).
    Les enchères seront ouvertes et clôturées sur appel explicite de ces deux méthodes, et on supposera qu'une fois clôturée, une enchère ne sera jamais réouverte (ce pourrait poser des problèmes puisqu'il faudra à nouveau rebloquer la somme maximum sur le compte de l'offre gagnante).


11. Implémentez la méthode `getOffreGagnante()` de la classe `Produit`. Elle devra renvoyer la meilleure offre d'enchère, si elle existe, et `null` sinon.


12. Écrivez la méthode `toString()` appropriée dans la classe `Compte`. Libre à vous de décider les informations à retourner, mais en ce qui concerne les offres du compte, seules les offres gagnantes actuelles du compte devraient être affichées.


13. Écrivez la méthode `toString()` appropriée dans la classe `Produit`. Parmi les différentes offres déposées, seule l'offre gagnante actuelle devrait être affichée.


14. Simulez votre application dans le programme principal (la classe `IBaille`). Pour cela, vous instancierez un produit et plusieurs comptes (3 au minimum). Pour chacun des comptes vous proposerez à l'utilisateur du logiciel (non-informaticien donc) de déposer des enchères pour ce produit en affichant les informations sur le produit et l'offre gagnante en cours.

    Pour récupérer les données saisies par l'utilisateur à la console, vous pouvez utiliser la classe `java.util.Scanner` qui permet de "parser" de manière intelligente une chaîne de caractères. Voici un petit exemple de ce que vous pouvez faire avec :

    ```java

    import java.util.Scanner;

    class IBaille {

        public static void main(String[] args) {

            // on attache un objet Scanner au flux d'entrée associée à la console
            Scanner saisie = new Scanner(System.in);
            System.out.println("Veuillez écrire quelque chose :");

            // récupération de la chaîne de caractères saisie par l'utilisateur
            String réponse = saisie.next();

            // récupération de la chaîne de caractères saisie par l'utilisateur sous forme d'un nombre entier
            int entier = saisie.nextInt();

        }

    }
    ```
    Pour plus de détails sur cette classe, voir l'API : https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html





15. Dessinez le diagramme de classes de votre application.
