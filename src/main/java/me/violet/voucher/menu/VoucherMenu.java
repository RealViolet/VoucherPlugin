package me.violet.voucher.menu;

import lombok.RequiredArgsConstructor;
import me.violet.voucher.Voucher;
import me.violet.voucher.profile.Profile;
import me.violet.voucher.utils.CC;
import me.violet.voucher.utils.ItemBuilder;
import me.violet.voucher.utils.menu.Button;
import me.violet.voucher.utils.menu.page.PagedMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class VoucherMenu extends PagedMenu {

    private Profile target;

    public VoucherMenu(Profile target) {
        this.target = target;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        List<Voucher> vouchers = new ArrayList<>(target.getVouchers());
        for (int index = 0; index < vouchers.size(); index++) {
            Voucher voucher = vouchers.get(index);
            buttons.put(index, new Button() {
                @Override
                public ItemStack getItem(Player player) {
                    return new ItemBuilder(!voucher.isRedeemed() ? Material.BOOK : Material.REDSTONE_BLOCK).setDisplayName(CC.RED + voucher.getName())
                            .setLore(CC.translate(Arrays.asList(
                                    "",
                                    "&6Name&f: " + voucher.getName(),
                                    "&6lAdded By&f: " + voucher.getAddedBy(),
                                    "&6Redeemed&f:" + voucher.isRedeemed(),
                                    "",
                                    (!voucher.isRedeemed() ? "&7&oClick to set voucher as redeemed." : "&7&oClick to set voucher as unredeemed.")
                            )))
                            .build();
                }

                @Override
                public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
                    voucher.setRedeemed(!voucher.isRedeemed());
                    target.save();
                    player.closeInventory();
                    openMenu(player);
                }
            });
        }
        return buttons;
    }

    @Override
    public String getRawTitle(Player player) {
        return CC.GOLD + player.getName() + "'s Vouchers";
    }
}
