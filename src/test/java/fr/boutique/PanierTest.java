package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PanierTest {

    @Test
    void ajouterArticleDoitAugmenterLeNombreDeArticles() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);

        // Agir
        panier.ajouterArticle(article, 2);

        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void calculerTotalDoitRetournerLaSommeDesSousTotaux() {
        // Arranger
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
        Article article2 = new Article("REF-002", "Cahier", 1.50);

        // Agir
        panier.ajouterArticle(article1, 3);
        panier.ajouterArticle(article2, 3);

        // Affirmer
        assertEquals(9.00, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierVideDoitRetournerEstVideEgalTrue() {
        // Arranger
        Panier panier = new Panier();

        // Agir
        boolean estVide = panier.estVide();

        // Affirmer
        assertTrue(estVide);
    }

    @Test
    void panierAvecArticlesNeDoitPasEtreVide() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);

        // Agir
        panier.ajouterArticle(article, 1);

        // Affirmer
        assertFalse(panier.estVide());
    }

    @Test
    void articleNulDoitLeverException() {
        // Arranger
        Panier panier = new Panier();

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(null, 1));
    }

    @Test
    void quantiteNulleDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, 0));
    }

    @Test
    void quantiteNegativeDoitLeverException() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo", 1.50);

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.ajouterArticle(article, -3));
    }

    @Test
    void codeReductionVideDoitLeverException() {
        // Arranger
        Panier panier = new Panier();

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(""));
    }

    @Test
    void codeReductionNulDoitLeverException() {
        // Arranger
        Panier panier = new Panier();

        // Agir / Affirmer
        assertThrows(IllegalArgumentException.class,
                () -> panier.appliquerCodeReduction(null));
    }

    @Test
    void quantiteUneDoitEtreAcceptee() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-010", "Agenda", 9.99);

        // Agir
        panier.ajouterArticle(article, 1);

        // Affirmer
        assertEquals(9.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void articleGratuitDoitEtreAccepte() {
        // Arranger
        Panier panier = new Panier();
        Article articleGratuit = new Article("OFFERT-01", "Stylo offert", 0.0);

        // Agir
        panier.ajouterArticle(articleGratuit, 1);

        // Affirmer
        assertEquals(0.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void prixEleveDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-999", "Ordinateur", 999.99);

        // Agir
        panier.ajouterArticle(article, 1);

        // Affirmer
        assertEquals(999.99, panier.calculerTotal(), 0.001);
    }

    @Test
    void panierAvecUnSeulArticleDoitFonctionner() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-050", "Classeur", 4.50);

        // Agir
        panier.ajouterArticle(article, 1);

        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void plusieursArticlesDifferentsDansPanier() {
        // Arranger
        Panier panier = new Panier();
        Article article1 = new Article("REF-101", "Stylo", 1.50);
        Article article2 = new Article("REF-102", "Cahier", 2.00);
        Article article3 = new Article("REF-103", "Classeur", 3.50);

        // Agir
        panier.ajouterArticle(article1, 2);
        panier.ajouterArticle(article2, 3);
        panier.ajouterArticle(article3, 1);

        // Affirmer
        assertEquals(12.50, panier.calculerTotal(), 0.001);
    }
}
