package fr.boutique;

/**
 * Représente une ligne dans le panier : un article et la quantité commandée.
 * Le sous-total est calculé par prix × quantité.
 */
public record LigneCommande(Article article, int quantite) {
    public LigneCommande {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null");
        }
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
    }

    /** Retourne le montant total pour cette ligne (prix × quantité). */
    public double sousTotal() {
        return article.getPrix() * quantite;
    }
}
