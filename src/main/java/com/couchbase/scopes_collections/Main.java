package com.couchbase.scopes_collections;

import com.couchbase.lite.*;

import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws CouchbaseLiteException, URISyntaxException {


        CouchbaseLite.init();
        System.out.println("CBL Initialized");
        Database.log.getConsole().setLevel(LogLevel.DEBUG);

        // <.>
        // Create/open a database
        Database database = new Database("travel-sample");
        System.out.println("Database created: travel-sample");

        // Create collections
        database.createCollection("airline", "inventory");
        database.createCollection("airport", "inventory");
        database.createCollection("hotel", "inventory");
        database.createCollection("landmark", "inventory");
        database.createCollection("route", "inventory");

/**
 * Create Replicator push and pull for collections
 */
        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(
                new URLEndpoint(new URI("wss://xxxxxxx.apps.cloud.couchbase.com:4984/travel-sample")))
                .addCollections(database.getScope("inventory").getCollections(), null)
                .setType(ReplicatorType.PUSH_AND_PULL)
                .setContinuous(true)
                .setAuthenticator(new BasicAuthenticator("Hatim", "xxxxx".toCharArray()));

        Replicator replicator = new Replicator(replConfig);

        // Listen to replicator change events.
        ListenerToken token = replicator.addChangeListener(change -> {
            System.out.println("Replicator state :: " + change.getStatus().getActivityLevel());
        });

        // Start replication.
        replicator.start();
    }

}

