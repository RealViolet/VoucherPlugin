package me.violet.voucher;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.violet.voucher.command.VoucherCommand;
import me.violet.voucher.mongo.MongoManager;
import me.violet.voucher.profile.ProfileListener;
import me.violet.voucher.utils.menu.MenuListener;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoucherPlugin extends JavaPlugin {

    @Getter
    private static VoucherPlugin instance;
    @Getter
    private MongoManager mongoManager;

    @Getter
    private List<ItemStack> items;

    @Override
    public void onEnable() {
        instance = this;
        items = new ArrayList<>();
        /* do configuration section

            run configuration section and create the itemstack and add it to the items list using list.add

        */

        mongoManager = new MongoManager();
        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ProfileListener(), this);
        new VoucherCommand(this);
    }

    @Override
    public void onDisable() {

    }


}
