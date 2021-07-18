package fr.altrix.achestexplorer.languages;

import fr.altrix.achestexplorer.*;

public class French implements ILanguages {
    @Override
    public String getLanguage() {
        return "fr";
    }

    @Override
    public String getGiveCommandParameters() {
        return "<joueur> <objet> <nombre>";
    }

    @Override
    public String getGiveCommandUtility() {
        return "vous permet de donner un item";
    }

    @Override
    public String getPlayerNotOnlineMessage() {
        return "§6ChestExplorer » §7Ce joueur n'est pas en ligne";
    }

    @Override
    public String getIdNotRecognizedMessage() {
        return "§6ChestExplorer » §7L'Id de l'item n'est pas reconnu";
    }

    @Override
    public String getPleaseEnterAnEntireNumberMessage() {
        return "§6ChestExplorer » §7Merci d'entrer un nombre entier";
    }

    @Override
    public String getGiveMessage() {
        return "§6ChestExplorer » §7Vous avez bien donner §6{number}§7 chestexplorer a §6{player}";
    }

    @Override
    public String getReloadMessage() {
        return "§6ChestExplorer » §7Le plugin a été rechargé";
    }

    @Override
    public String getReloadCommandUtility() {
        return "vous permet de reload le plugin";
    }
}
