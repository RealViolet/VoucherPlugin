package me.violet.voucher;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.sun.org.apache.regexp.internal.REDebugCompiler;
import lombok.Data;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class Voucher {


    private final String name;
    private final String addedBy;
    @Setter
    private boolean redeemed;

    public Voucher(String name, String addedBy, boolean redeemed) {
        this.name = name;
        this.addedBy = addedBy;
        this.redeemed = redeemed;
    }

    public Voucher(Document document) {
        this.name = document.getString("name");
        this.addedBy = document.getString("addedBy");
        this.redeemed = document.getBoolean("redeemed");
    }


    public Document toBson() {
        return new Document()
                .append("name", this.name)
                .append("addedBy", this.addedBy)
                .append("redeemed", this.redeemed);
    }

    public String toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", this.name);
        jsonObject.addProperty("addedBy", this.addedBy);
        jsonObject.addProperty("redeemed", this.redeemed);
        return jsonObject.toString();
    }

    public static Voucher fromJson(String string) {
        JsonObject json = new JsonParser().parse(string).getAsJsonObject();
        String name = json.get("name").getAsString();
        String addedBy = json.get("addedBy").getAsString();
        boolean redeemed = json.get("redeemed").getAsBoolean();
        return new Voucher(name, addedBy, redeemed);
    }

}
