package fr.boutique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceCommandeTest {

    private ServiceCommande service;
    private Panier panier;
    private Article articleTest;

    @BeforeEach
    void initialiser() {
        DepotStock stockDisponible = reference -> 100;
        service = new ServiceCommande(stockDisponible);
        panier = new Panier();
        articleTest = new Article("REF-001", "Stylo", 2.50);
    }

    @Test
    void commandeValideDoitRetournerUneCommande() {
        // Arranger
        panier.ajouterArticle(articleTest, 2);

        // Agir
        Commande commande = service.passerCommande(panier, "CLIENT-42");

        // Affirmer
        assertNotNull(commande);
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {
        // Arranger — panier vide par défaut

        // Agir / Affirmer
        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, "CLIENT-42"));
    }

    @Test
    void identifiantClientNulDoitLeverException() {
        // Arranger
        panier.ajouterArticle(articleTest, 1);

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, null));
    }

    @Test
    void identifiantClientVideDoitLeverException() {
        // Arranger
        panier.ajouterArticle(articleTest, 1);

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> service.passerCommande(panier, ""));
    }

    @Test
    void stockInsuffisantDoitLeverStockInsuffisantException() {
        // Arranger
        DepotStock stockLimite = reference -> 1;
        ServiceCommande serviceStockLimite = new ServiceCommande(stockLimite);
        panier.ajouterArticle(articleTest, 5);

        // Agir / Affirmer
        assertThrows(StockInsuffisantException.class,
                () -> serviceStockLimite.passerCommande(panier, "CLIENT-42"));
    }

    @Test
    void totalCommandeDoitCorrespondreAuTotalDuPanier() {
        // Arranger
        panier.ajouterArticle(articleTest, 4);

        // Agir
        Commande commande = service.passerCommande(panier, "CLIENT-42");

        // Affirmer
        assertEquals(panier.calculerTotal(), commande.total(), 0.001);
    }
}
