package fr.altrix.achestexplorer;

import de.tr7zw.changeme.nbtapi.*;
import fr.altrix.achestexplorer.command.*;
import fr.altrix.achestexplorer.enchant.*;
import fr.altrix.achestexplorer.explorer.*;
import fr.altrix.achestexplorer.languages.*;
import fr.altrix.achestexplorer.listeners.*;
import fr.altrix.achestexplorer.util.*;
import fr.better.commands.*;
import fr.better.commands.complex.*;
import fr.better.commands.complex.content.*;
import fr.mrmicky.fastinv.*;
import org.bstats.bukkit.*;
import org.bstats.charts.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.java.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public final class AChestExplorer extends JavaPlugin {

    public ILanguages language;

    public List<ChestExplorer> chestExplorerList;

    public boolean upToDate;

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 12064);
        saveDefaultConfig();

        new VersionChecker(this, 83).getVersion(version -> {
            upToDate = version.equals(getDescription().getVersion());
            System.out.println(version);
        });
        setup();
    }

    private void setup() {
        registerGlow();
        FastInvManager.register(this);

        setupChestExplorers();
        setupLanguage();
        setupListeners();

        CommandsBuilder commandsBuilder = CommandsBuilder.init(this);
        Command command = commandsBuilder.createComplexCommand("chestexplorer");
        command.add("give", new GiveArgs(this), ArgumentType.DONT_NEED_PLAYER, language.getGiveCommandParameters());
        command.add("reload", new ReloadArgs(this));
    }

    private void setupLanguage() {
        if (getConfig().getString("language").equalsIgnoreCase("fr")) language = new French();
        else if (getConfig().getString("language").equalsIgnoreCase("en")) language = new English();
    }

    private void setupChestExplorers() {
        chestExplorerList = new ArrayList<>();
        for (String name : getConfig().getConfigurationSection("items").getKeys(false)) {
            chestExplorerList.add(new ChestExplorer(getConfig().getConfigurationSection("items." + name)));
        }
    }

    private void setupListeners() {
        Bukkit.getPluginManager().registerEvents(new InteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new UpdateListener(this), this);
    }

    private void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);

            Enchantment.registerEnchantment(new Glow(6352));

            f.setAccessible(false);
        } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException e){e.printStackTrace(); }
    }

    public ChestExplorer getChestExplorerById(String id) {
        for (ChestExplorer chestExplorer : chestExplorerList) {
            if (chestExplorer.getId().equalsIgnoreCase(id))
                return chestExplorer;
        }
        return null;
    }

    public ItemStack getItemByChestExplorer(ChestExplorer chestExplorer) {
        try {
            ItemStack itemStack = new ItemStack(chestExplorer.getMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(chestExplorer.getName());
            itemMeta.setLore(chestExplorer.getLores());
            itemMeta.spigot().setUnbreakable(true);
            itemMeta.addEnchant(new Glow(6352), new Random().nextInt(500000), true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            itemStack.setItemMeta(itemMeta);

            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setString("chestexplorer_id", chestExplorer.getId());
            nbtItem.setInteger("chestexplorer_uses", chestExplorer.getUses());
            NBTFile nbtFile = new NBTFile(new File(getDataFolder(), "nbt.nbt"));
            nbtFile.save();
            return nbtItem.getItem();
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }

    public void reloadPlugin() {
        reloadConfig();
        setupLanguage();
        setupChestExplorers();
    }
}
