package me.violet.voucher.utils.menu.buttons;

import me.violet.voucher.utils.CC;
import me.violet.voucher.utils.ItemBuilder;
import me.violet.voucher.utils.menu.Button;
import me.violet.voucher.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;


public class BackButton extends Button {

    private Menu menu;

    public BackButton(Menu menu) {
        this.menu = menu;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.REDSTONE).setDisplayName(CC.DRED + "Go Back").build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        menu.openMenu(player);
    }
}
