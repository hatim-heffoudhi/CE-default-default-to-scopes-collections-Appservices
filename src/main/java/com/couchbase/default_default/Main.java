package com.couchbase.default_default;

import com.couchbase.lite.*;

import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws CouchbaseLiteException, URISyntaxException {

        // Init of the CouchbaseLite
        CouchbaseLite.init();
        System.out.println("CBL Initialized");
        Database.log.getConsole().setLevel(LogLevel.DEBUG);
        // Create a database travel-sample
        Database database = new Database("travel-sample");

        Collection defaultCollection =  database.getDefaultCollection();
        // Create a new collection (like a SQL table) in the database.
        System.out.println("using default collection " + defaultCollection);

        // Create Replicator for Default Collection
        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(
                new URLEndpoint(new URI("ws://xxxx-sgw.aws.unix.us.com:4984/travel-sample")))
                .addCollection(defaultCollection, null)
                .setType(ReplicatorType.PUSH_AND_PULL)
                .setContinuous(true)
                .setAuthenticator(new BasicAuthenticator("Hatim", "xxxx".toCharArray()));

        // config replicator
        Replicator replicator = new Replicator(replConfig);

        // Listen to replicator change events.
        ListenerToken token = replicator.addChangeListener(change -> {
            System.out.println("Replicator state :: " + change.getStatus().getActivityLevel());
        });

        // Start replication.
        replicator.start();
    }

}

