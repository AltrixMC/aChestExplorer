package fr.altrix.achestexplorer.command;

import fr.altrix.achestexplorer.*;
import fr.better.commands.complex.content.*;
import org.bukkit.entity.*;

import java.util.*;

public class ReloadArgs implements Argument {

    AChestExplorer main;
    public ReloadArgs(AChestExplorer main) { this.main = main; }

    @Override
    public String execute(Player player, List<String> parameters) {
        main.reloadPlugin();
        return main.language.getReloadMessage();
    }

    @Override
    public String utility() {
        return main.language.getReloadCommandUtility();
    }
}
