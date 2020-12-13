package me.violet.voucher.profile;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Data;
import lombok.Getter;
import me.violet.voucher.Voucher;
import me.violet.voucher.VoucherPlugin;
import me.violet.voucher.mongo.MongoManager;
import org.bson.Document;
import org.bukkit.entity.Player;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
public class Profile {

    @Getter
    private static Map<UUID, Profile> profiles = new ConcurrentHashMap<>();

    private UUID uuid;
    private String name;
    private List<Voucher> vouchers;

    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.load();
    }

    public void load() {
        Document document = VoucherPlugin.getInstance().getMongoManager().getProfiles().find(Filters.eq("uuid", this.uuid.toString())).first();
        if (document != null) {
            if (this.name == null) {
                this.name = document.getString("name");
            }
            this.vouchers = new ArrayList<>();
            vouchers = (document.getList("vouchers", Document.class).stream().map(Voucher::new).collect(Collectors.toList()));
            profiles.put(this.uuid, this);
        }
    }

    public void save() {
        Document document = new Document();
        document.append("uuid", this.uuid.toString());
        document.append("name", this.name);
        document.append("vouchers", vouchers.stream().map(Voucher::toBson).collect(Collectors.toList()));
        VoucherPlugin.getInstance().getMongoManager().getProfiles()
                .replaceOne(Filters.eq("uuid", this.uuid.toString()), document, new UpdateOptions().upsert(true));
    }

    public static Profile getByPlayer(Player player) {
        if (profiles.containsKey(player.getUniqueId())) {
            return profiles.get(player.getUniqueId());
        }
        return new Profile(player.getUniqueId(), player.getName());
    }

    public void addVoucher(Voucher voucher) {
        this.vouchers.add(voucher);
    }

}
