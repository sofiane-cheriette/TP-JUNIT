package fr.boutique;

import java.time.LocalDateTime;

/**
 * Service métier qui gère la création de commandes.
 * Il vérifie que le panier n'est pas vide, que le client est valide
 * et que le stock est suffisant avant de créer la commande.
 */
public class ServiceCommande {
    private final DepotStock depotStock;

    public ServiceCommande(DepotStock depotStock) {
        this.depotStock = depotStock;
    }

    /**
     * Crée une commande à partir du panier et de l'identifiant client.
     * Lève IllegalStateException si le panier est vide.
     * Lève IllegalArgumentException si l'identifiant client est null ou vide.
     * Lève StockInsuffisantException si un article manque en stock.
     */
    public Commande passerCommande(Panier panier, String identifiantClient) {
        if (panier.estVide()) {
            throw new IllegalStateException("Impossible de commander : le panier est vide");
        }
        if (identifiantClient == null || identifiantClient.isBlank()) {
            throw new IllegalArgumentException("L'identifiant client est invalide");
        }

        for (LigneCommande ligne : panier.getLignes()) {
            int stockDisponible = depotStock.getStock(ligne.article().getReference());
            if (stockDisponible < ligne.quantite()) {
                throw new StockInsuffisantException(
                        "Stock insuffisant pour : " + ligne.article().getNom()
                );
            }
        }

        return new Commande(identifiantClient, panier.calculerTotal(), LocalDateTime.now());
    }
}
