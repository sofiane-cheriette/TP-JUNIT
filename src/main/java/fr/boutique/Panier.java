package fr.boutique;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente le panier d'achat d'un client.
 * On peut y ajouter des articles, appliquer un code de réduction
 * et calculer le total. Codes acceptés : REDUC10 (-10%) et REDUC20 (-20%).
 */
public class Panier {
    private final List<LigneCommande> lignes = new ArrayList<>();
    private String codeReduction = null;

    /** Ajoute un article au panier avec la quantité souhaitée. */
    public void ajouterArticle(Article article, int quantite) {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null");
        }
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        lignes.add(new LigneCommande(article, quantite));
    }

    /** Applique un code de réduction au panier (REDUC10 ou REDUC20). */
    public void appliquerCodeReduction(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Le code de réduction est invalide");
        }
        this.codeReduction = code;
    }

    /** Calcule et retourne le total du panier, réduction incluse si un code a été appliqué. */
    public double calculerTotal() {
        double sousTotal = lignes.stream()
                .mapToDouble(LigneCommande::sousTotal)
                .sum();

        if ("REDUC10".equals(codeReduction)) {
            return sousTotal * 0.90;
        }
        if ("REDUC20".equals(codeReduction)) {
            return sousTotal * 0.80;
        }
        return sousTotal;
    }

    public int nombreArticles() {
        return lignes.size();
    }

    public boolean estVide() {
        return lignes.isEmpty();
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }
}
