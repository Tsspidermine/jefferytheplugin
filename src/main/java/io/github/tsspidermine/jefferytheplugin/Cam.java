package io.github.tsspidermine.jefferytheplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Cam implements CommandExecutor {

    List<String> toggled = new ArrayList<String>();
    Map<String, Location> playerLocations = new HashMap<String, Location>();
    Map<String, GameMode> playerGameModes = new HashMap<String, GameMode>();
    

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(toggled.contains(player.getName())){
                player.sendMessage("Exiting camera mode!");
                player.teleport(playerLocations.get(player.getName()));
                player.setGameMode(playerGameModes.get(player.getName()));
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                toggled.remove(player.getName());
                return true;
            } 
            player.sendMessage("Entering camera mode!");
            playerLocations.put(player.getName(), player.getLocation());
            playerGameModes.put(player.getName(), player.getGameMode());
            player.setGameMode(GameMode.SPECTATOR);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 1000000, 1, false, false, false));
            toggled.add(player.getName());
            return true;
        } else{
            Bukkit.getLogger().warning("This command can only be used by a player!");
            return false;
        }
    }
    
}
