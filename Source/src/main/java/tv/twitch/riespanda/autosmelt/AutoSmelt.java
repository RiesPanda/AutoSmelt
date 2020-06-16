package tv.twitch.riespanda.autosmelt;

import org.bukkit.plugin.java.JavaPlugin;
import tv.twitch.riespanda.autosmelt.commands.GivePickCommand;
import tv.twitch.riespanda.autosmelt.events.BlockBreak;

public final class AutoSmelt extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("AutoSmelt by RiesPanda has been fired up!");

        getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
        getCommand("give").setExecutor(new GivePickCommand());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
