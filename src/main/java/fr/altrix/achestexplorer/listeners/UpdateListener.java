package fr.altrix.achestexplorer.listeners;

import fr.altrix.achestexplorer.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.*;

public class UpdateListener implements Listener {

    AChestExplorer main;
    public UpdateListener(AChestExplorer main) {this.main = main;}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp() && !main.upToDate) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage("§6ChestExplorer » §8The plugin is no longer up to date ! Please download it on groupez");
                    TextComponent textComponent = new TextComponent("https://groupez.dev/resources/chestexplorer.83");
                    textComponent.setColor(ChatColor.GOLD);
                    textComponent.setBold(true);
                    textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://groupez.dev/resources/chestexplorer.83"));
                    player.spigot().sendMessage(textComponent);
                }
            }.runTaskLater(main, 30L);
        }
    }

}
