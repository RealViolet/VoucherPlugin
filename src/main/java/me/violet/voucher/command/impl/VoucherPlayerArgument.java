package me.violet.voucher.command.impl;

import lombok.RequiredArgsConstructor;
import me.violet.voucher.VoucherPlugin;
import me.violet.voucher.menu.VoucherMenu;
import me.violet.voucher.profile.Profile;
import me.violet.voucher.utils.CC;
import me.violet.voucher.utils.command.CommandInfo;
import me.violet.voucher.utils.command.argument.CommandArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@CommandInfo(names = {"player"}, permission = "violet.voucher.player", usage = "player <player>", description = "Shows a players vouchers", playerOnly = true)
public class VoucherPlayerArgument extends CommandArgument {

    private final VoucherPlugin instance;

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(CC.createUsage(label, "player <player>"));
            return;
        }
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(CC.RED + "Player isn't online");
            return;
        }
        Profile target = Profile.getByPlayer(player);
        new VoucherMenu(target).openMenu((Player) sender);

    }
}
