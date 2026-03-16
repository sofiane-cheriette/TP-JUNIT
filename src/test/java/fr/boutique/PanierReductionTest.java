package fr.boutique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
        "       , 100.0",
        "REDUC10,  90.0",
        "REDUC20,  80.0"
    })
    void calculerTotalAvecCodeReductionDoitAppliquerLaBonneRemise(String code, double totalAttendu) {
        // Arranger — panier avec un article à 100 € (10 × 10.00)
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Produit test", 10.00);
        panier.ajouterArticle(article, 10);

        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        // Agir
        double total = panier.calculerTotal();

        // Affirmer
        assertEquals(totalAttendu, total, 0.001);
    }
}
