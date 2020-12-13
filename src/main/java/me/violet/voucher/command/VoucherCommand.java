package me.violet.voucher.command;

import me.violet.voucher.VoucherPlugin;
import me.violet.voucher.command.impl.*;
import me.violet.voucher.utils.command.CommandInfo;
import me.violet.voucher.utils.command.argument.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandInfo(names = {"voucher"}, permission = "violet.voucher")
public class VoucherCommand extends CommandExecutor {

    private VoucherPlugin instance;

    public VoucherCommand(VoucherPlugin instance) {
        super(instance);
        this.addArgument(new VoucherPlayerArgument(instance));
        this.addArgument(new VoucherGiveArgument(instance));
    }

    @Override
    public boolean executeOther(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
