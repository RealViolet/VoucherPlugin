package me.violet.voucher.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;

import lombok.Getter;
import org.bson.Document;
@Data
public class MongoManager {

    public MongoClient mongoClient;
    public MongoDatabase mongoDatabase;
    public MongoCollection<Document> vouchers;
    public MongoCollection<Document> profiles;

    public MongoManager() {
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        mongoDatabase = mongoClient.getDatabase("Voucher");
        vouchers = mongoDatabase.getCollection("vouchers");
        profiles = mongoDatabase.getCollection("profiles");
    }

}
