package fr.altrix.achestexplorer.util;

import fr.altrix.achestexplorer.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class VersionChecker implements Listener {

    private final String URL_API = "https://groupez.dev/api/v1/resource/version/%s";
    private final AChestExplorer plugin;
    private final int pluginID;

    public VersionChecker(AChestExplorer plugin, int pluginID) {
        super();
        this.plugin = plugin;
        this.pluginID = pluginID;
    }

    public void getVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            final String apiURL = String.format(URL_API, this.pluginID);
            try {
                URL url = new URL(apiURL);
                URLConnection hc = url.openConnection();
                hc.setRequestProperty("User-Agent", "Mozilla/5.0");
                Scanner scanner = new Scanner(hc.getInputStream());
                if (scanner.hasNext())
                    consumer.accept(scanner.next());
                scanner.close();

            } catch (IOException exception) {
                this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }
}