package io.github.tsspidermine.jefferytheplugin;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoining implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage(ChatColor.YELLOW + "Hello, " + event.getPlayer().getName() + "! You have been alive for " + (event.getPlayer().getStatistic(Statistic.TIME_SINCE_DEATH) / 20) / 60 + " minute(s)! Your record is " + App.plugin.getConfig().getInt(event.getPlayer().getUniqueId().toString()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        App.plugin.saveConfig();
    }
    
}
