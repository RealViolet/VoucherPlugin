package me.violet.voucher.command.impl;

import lombok.RequiredArgsConstructor;
import me.violet.voucher.Voucher;
import me.violet.voucher.VoucherPlugin;
import me.violet.voucher.profile.Profile;
import me.violet.voucher.utils.CC;
import me.violet.voucher.utils.command.CommandInfo;
import me.violet.voucher.utils.command.argument.CommandArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@CommandInfo(names = "give", permission = "violet.voucher.give", usage = "give <player> <voucher>", description = "Give a player a voucher")
public class VoucherGiveArgument extends CommandArgument {

    private final VoucherPlugin instance;

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(CC.createUsage(label, "give <player> <voucher>"));
            return;
        }
        Player targetplayer = Bukkit.getPlayer(args[1]);
        if (targetplayer == null) {
            sender.sendMessage(CC.RED + "This player is offline!");
            return;
        }
        String addedBy = "Console";
        if (sender instanceof Player) {
            addedBy = sender.getName();
        }
        StringBuilder voucher = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            voucher.append(args[i]).append(" ");
        }
        Profile target = Profile.getByPlayer(targetplayer);
        target.addVoucher(new Voucher(voucher.toString(), addedBy, false));
        sender.sendMessage(CC.GREEN + "You have given " + target.getName() + " the \"" + voucher.toString() + "\" voucher.");
    }
}