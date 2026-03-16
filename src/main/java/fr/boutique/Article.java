package fr.boutique;

/**
 * Représente un article du catalogue.
 * Chaque article a une référence unique, un nom et un prix.
 * La référence et le nom ne peuvent pas être vides, le prix ne peut pas être négatif.
 */
public class Article {
    private final String reference;
    private final String nom;
    private double prix;

    /** Crée un article. Lève IllegalArgumentException si les valeurs sont invalides. */
    public Article(String reference, String nom, double prix) {
        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("La référence ne peut pas être vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    /** Modifie le prix. Lève IllegalArgumentException si le nouveau prix est négatif. */
    public void setPrix(double nouveauPrix) {
        if (nouveauPrix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.prix = nouveauPrix;
    }

    @Override
    public String toString() {
        return nom + " (" + reference + ") — " + prix + " €";
    }
}
