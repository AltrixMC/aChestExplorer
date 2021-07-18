package fr.altrix.achestexplorer.languages;

import fr.altrix.achestexplorer.*;

public class English implements ILanguages {
    @Override
    public String getLanguage() {
        return "en";
    }

    @Override
    public String getGiveCommandParameters() {
        return "<player> <item> <number>";
    }

    @Override
    public String getGiveCommandUtility() {
        return "allows you to give an item";
    }

    @Override
    public String getPlayerNotOnlineMessage() {
        return "§6ChestExplorer » §7Player not online";
    }

    @Override
    public String getIdNotRecognizedMessage() {
        return "§6ChestExplorer » §7Id is not recognized";
    }

    @Override
    public String getPleaseEnterAnEntireNumberMessage() {
        return "§6ChestExplorer » §7Please enter an entire number";
    }

    @Override
    public String getGiveMessage() {
        return "§6ChestExplorer » §7You just gave §6{number}§7 chestexplorer to §6{player}";
    }

    @Override
    public String getReloadMessage() {
        return "§6ChestExplorer » §7The plugin has been reloaded";
    }

    @Override
    public String getReloadCommandUtility() {
        return "allows you to reload the plugin";
    }
}
