package fr.altrix.achestexplorer.listeners;

import de.tr7zw.changeme.nbtapi.*;
import fr.altrix.achestexplorer.*;
import fr.altrix.achestexplorer.explorer.*;
import fr.mrmicky.fastinv.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.io.*;
import java.util.*;

public class InteractListener implements Listener {

    AChestExplorer main;
    public InteractListener(AChestExplorer main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && (
                event.getClickedBlock().getType().equals(Material.CHEST) ||
                event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST))) {
            if (event.getItem() != null) {
                NBTItem nbtItem = new NBTItem(event.getItem());
                if (nbtItem.hasKey("chestexplorer_id")) {
                    event.setCancelled(true);
                    Chest chest = (Chest) event.getClickedBlock().getState();

                    FastInv fastInv = new FastInv(chest.getInventory().getSize(), " ");
                    for (int i = 0; i < chest.getInventory().getSize(); i++) {
                        fastInv.setItem(i, chest.getInventory().getContents()[i], event1 -> event1.setCancelled(true));
                    }
                    fastInv.open(event.getPlayer());

                    try {
                        NBTFile nbtFile = new NBTFile(new File(main.getDataFolder(), "nbt.nbt"));
                        ChestExplorer chestExplorer = main.getChestExplorerById(nbtItem.getString("chestexplorer_id"));

                        int uses = nbtItem.getInteger("chestexplorer_uses") - 1;
                        nbtItem.setInteger("chestexplorer_uses", uses);

                        ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
                        itemMeta.setLore(chestExplorer.getLores(uses));

                        nbtItem.getItem().setItemMeta(itemMeta);
                        if (uses < 0)
                            nbtItem.getItem().setType(Material.AIR);
                        nbtFile.save();
                    } catch (IOException e) { e.printStackTrace(); }
                    event.getPlayer().setItemInHand(nbtItem.getItem());
                }
            }
        }
    }

}
