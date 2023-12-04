package com.example.functions;

import com.example.database.Database;
import com.example.schemas.User;

public interface GetHistory {
    default void getHistory(Database database,User user){
        System.out.println("\nFlight History for "+user.name+" :\n");
        database.getUserHistory(user);
    }
}
