package io.github.tsspidermine.jefferytheplugin;



import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin
{
    public static App plugin;


    @Override
    public void onEnable(){
        plugin = this;
        this.saveDefaultConfig();
        getLogger().info("Hello, Y'all!");
        getServer().getPluginManager().registerEvents(new PlayerJoining(), this);
        this.getCommand("timealive").setExecutor(new TimeAlive());
        this.getCommand("cam").setExecutor(new Cam());
        
    }

    @Override
    public void onDisable(){
        getLogger().info("See you again!");
        this.saveConfig();
    }
}
