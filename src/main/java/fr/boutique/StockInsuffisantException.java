package fr.boutique;

/**
 * Exception levée quand le stock disponible est inférieur à la quantité demandée.
 * C'est une RuntimeException donc elle n'a pas besoin d'être déclarée dans la signature des méthodes.
 */
public class StockInsuffisantException extends RuntimeException {
    public StockInsuffisantException(String message) {
        super(message);
    }
}
