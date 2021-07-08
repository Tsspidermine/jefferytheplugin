package io.github.tsspidermine.jefferytheplugin;


import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TimeAlive implements CommandExecutor {
    Integer recordTime;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            FileConfiguration config = App.plugin.getConfig();
            recordTime = config.getInt(player.getUniqueId().toString());
            Integer timeAlive = (player.getStatistic(Statistic.TIME_SINCE_DEATH) / 20) / 60;
            if (timeAlive > recordTime){
                recordTime = timeAlive;
                config.set(player.getUniqueId().toString(), recordTime);
                App.plugin.saveConfig();
            }
            player.sendMessage("You have been alive for " + timeAlive + " minute(s)! Your record is " + recordTime);
            

        } else {
            Bukkit.getLogger().warning("This command can only be used by a player!");
        }

        return true;
    }
}
