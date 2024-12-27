package hm.ceres.command.commands;

import hm.ceres.HotMap;
import hm.ceres.command.BaseTabCommand;
import hm.ceres.command.ModeMap;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class CreateImageCommand extends BaseTabCommand {
    public CreateImageCommand() {
        super("hotmap",
                "/createImage",
                "hotmap.command.createimage",
                "Creas la imagen con el mapa de calor");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        switch (args[0].toLowerCase()){
            case "create" -> {
                new BukkitRunnable() {
                    public void run() {
                        HotMap.CreateImage(ModeMap.valueOf(args[1].toUpperCase()));
                        sender.sendMessage("Fue creado con exitosamente");
                    }
                }.runTaskAsynchronously(HotMap.getInstance());
            }
            case "stop" -> {
                if (!HotMap.running){
                    sender.sendMessage("Ya estaba detenido");
                }else {
                    sender.sendMessage("Se detuvo");
                    HotMap.running = false;
                }
            }
            case "start" -> {
                if (HotMap.running){
                    sender.sendMessage("Ya estaba corriendo");
                }else {
                    sender.sendMessage("Comenzó a correr");
                    HotMap.running = true;
                }
            }
            default -> sender.sendMessage("Error con los argumentos");
        }

    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        switch (args.length) {
            case 1 -> {
                return listTab(args[0], List.of("create", "stop", "start"));
            }
            case 2 -> {
                return listTab(args[1], enumsToStrings(ModeMap.values(), true));
            }
        }
        return null;
    }
}