package fr.altrix.achestexplorer.explorer;

import org.bukkit.*;
import org.bukkit.configuration.*;

import java.util.*;
import java.util.stream.*;

public class ChestExplorer {

    private ConfigurationSection section;
    private String id;
    private Material material;
    private String name;
    private List<String> lores;
    private int uses;
    private boolean glow;

    public ChestExplorer(ConfigurationSection section) {
        this.section = section;
        this.id = section.getName();
        this.material = Material.getMaterial(section.getInt("id"));
        this.name = ChatColor.translateAlternateColorCodes('&', section.getString("name"));
        this.uses = section.getInt("uses");
        this.lores =
                section.getStringList("lores").stream().map(s ->
                        ChatColor.translateAlternateColorCodes('&', s.replace("{uses}", String.valueOf(uses))))
                        .collect(Collectors.toList());

        this.glow = section.getBoolean("glowing");
    }

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLores() {
        return lores;
    }

    public int getUses() {
        return uses;
    }

    public List<String> getLores(int uses) {
        return section.getStringList("lores").stream().map(s ->
                ChatColor.translateAlternateColorCodes('&', s.replace("{uses}", String.valueOf(uses))))
                .collect(Collectors.toList());
    }

    public boolean isGlow() {
        return glow;
    }
}
