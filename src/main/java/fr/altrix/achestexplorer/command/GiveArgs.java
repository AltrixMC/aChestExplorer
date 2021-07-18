package fr.altrix.achestexplorer.command;

import fr.altrix.achestexplorer.*;
import fr.altrix.achestexplorer.explorer.*;
import fr.better.commands.complex.*;
import fr.better.commands.complex.content.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

public class GiveArgs implements Argument {

    AChestExplorer main;
    public GiveArgs(AChestExplorer aChestExplorer) {
        this.main = aChestExplorer;
    }

    @Override
    public String execute(Player player, List<String> parameters) {

        if (Bukkit.getPlayer(parameters.get(0)) == null) return main.language.getPlayerNotOnlineMessage();
        Player playerToGive = Bukkit.getPlayer(parameters.get(0));

        if (main.getChestExplorerById(parameters.get(1)) == null) return main.language.getIdNotRecognizedMessage();
        ChestExplorer chestExplorer = main.getChestExplorerById(parameters.get(1));

        try {Integer.parseInt(parameters.get(2));}
        catch (Exception e) {return main.language.getPleaseEnterAnEntireNumberMessage();}
        int numberToGive = Integer.parseInt(parameters.get(2));

        ItemStack chestExplorerItem = main.getItemByChestExplorer(chestExplorer);
        for (int i = 0; i < numberToGive; i++) {
            playerToGive.getInventory().addItem(chestExplorerItem);
        }

        return main.language.getGiveMessage()
                .replace("{number}", String.valueOf(numberToGive))
                .replace("{player}", playerToGive.getName());
    }

    @Override
    public String utility() {
        return main.language.getGiveCommandUtility();
    }
}
