package org.example.database;


import org.example.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseCreateService {

    public void initDB(Database database){
        String initDbFilename = new Utils().getString(Utils.INIT_DB_SQL_FILE_PATH);
        try {
            String sql = String.join(
                    "\n",
                    Files.readString(Paths.get(initDbFilename))
            );


            database.executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
