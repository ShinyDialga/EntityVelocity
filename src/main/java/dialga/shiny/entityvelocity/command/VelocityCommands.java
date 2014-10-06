package dialga.shiny.entityvelocity.command;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.NestedCommand;
import dialga.shiny.entityvelocity.util.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityCommands {

    public static class VelocityCommand {
        @CommandPermissions("velocity.velocity")
        @Command(
                aliases = {"velocity", "vel"},
                desc = "Apply velocity to entities!",
                min = 1
        )
        @NestedCommand(VelocityCommands.class)
        public static void velocity() {
        }
    }

    static String velocityString = ChatColor.GOLD + "" + ChatColor.BOLD + "Velocity" + ChatColor.GRAY + " >> ";
    static String velocityApplies = ChatColor.RESET + "" + ChatColor.RED + "Applied ";

    @CommandPermissions("velocity.velocity.entity")
    @Command(
            aliases = {"entity", "e"},
            desc = "Apply velocity to a created entity!",
            min = 1,
            max = 8,
            usage = "[entity] [# of entity] [velX] [velY] [velZ] [x] [y] [z]"
    )
    public static void entityVelocity(final CommandContext args, CommandSender sender) throws Exception {

        EntityType entityType = EntityType.SHEEP;
        try {
            entityType = EntityUtil.getEntityType(args.getString(0, "SHEEP"));
        } catch (NullPointerException npe) {
            sender.sendMessage(ChatColor.RED + "The entity wasn't identified");
            npe.printStackTrace();
            return;
        }
        Player player = (Player) sender;
        int quantity = args.getInteger(1, 1);
        double velX = args.getDouble(2, 0);
        double velY = args.getDouble(3, 0);
        double velZ = args.getDouble(4, 0);
        double x = args.getDouble(5, player.getLocation().getX());
        double y = args.getDouble(6, player.getLocation().getY());
        double z = args.getDouble(7, player.getLocation().getZ());

        Location location = new Location(player.getWorld(), x, y, z);
        Vector vector = new Vector(velX, velY, velZ);

        for (int i = 0; i < quantity; i++) {
            Entity entity = player.getWorld().spawnEntity(location, entityType);
            entity.setVelocity(vector);
        }

        sender.sendMessage(velocityString + velocityApplies
                + velX + ", " + velY + ", " + velZ
                + " to created " + ChatColor.GOLD + entityType
                + ChatColor.RED + " at " + x + ", " + y + ", " + z);
        return;
    }

    @CommandPermissions("velocity.velocity.player")
    @Command(
            aliases = {"player", "p"},
            desc = "Apply velocity to a certain player!",
            min = 1,
            max = 4,
            usage = "[player] [velX] [velY] [velZ]"
    )
    public static void playerVelocity(final CommandContext args, CommandSender sender) throws Exception {

        Player target = null;
        try {
            target = Bukkit.getPlayer(args.getString(0));
        } catch (NullPointerException npe) {
            sender.sendMessage(ChatColor.RED + "The player couldn't be found");
            npe.printStackTrace();
            return;
        }
        double velX = args.getDouble(1, 0);
        double velY = args.getDouble(2, 0);
        double velZ = args.getDouble(3, 0);

        Vector vector = new Vector(velX, velY, velZ);
        target.setVelocity(vector);

        sender.sendMessage(velocityString + velocityApplies
                + velX + ", " + velY + ", " + velZ
                + " to " + ChatColor.RESET + target.getDisplayName());
        if (!((Player)sender).equals(target)) {
            target.sendMessage(velocityString + velocityApplies
                    + velX + ", " + velY + ", " + velZ
                    + " to " + ChatColor.GOLD + "You!");
        }
        return;
    }

    @CommandPermissions("velocity.velocity.types")
    @Command(
            aliases = {"types", "t"},
            desc = "View a list of usable mobs to summon!",
            max = 0
    )
    public static void entityTypes(final CommandContext args, CommandSender sender) throws Exception {
        String message = velocityString + ChatColor.RED
                + "The following list may have mobs that won't work or are outdated:\n"
                + ChatColor.GOLD;
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getName() != null) {
                message = message + entityType.getEntityClass().getSimpleName() + ", ";
            }
        }
        message = message.substring(0, message.length() - 2);
        sender.sendMessage(message);
        return;
    }


}
