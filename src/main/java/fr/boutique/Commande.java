package fr.boutique;

import java.time.LocalDateTime;

/**
 * Représente une commande validée.
 * Contient l'identifiant du client, le montant total et la date de création.
 * C'est un record Java : immuable, pas de setters.
 */
public record Commande(String identifiantClient, double total, LocalDateTime dateCreation) {
}
