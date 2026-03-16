package fr.boutique;

/**
 * Interface représentant l'accès au stock.
 * Permet de récupérer la quantité disponible pour un article donné.
 * En test, on la remplace par un stub (ex. : reference -> 100).
 */
public interface DepotStock {
    /** Retourne le stock disponible pour la référence donnée. */
    int getStock(String referenceArticle);
}
