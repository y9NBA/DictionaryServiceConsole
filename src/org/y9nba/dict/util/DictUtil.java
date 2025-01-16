package org.y9nba.dict.util;

import java.io.File;

public class DictUtil {
    public static String getDictionariesPath() {
        String userHome = System.getProperty("user.home");
        File appHome = new File(userHome, "\\Dictionaries");

        if (!appHome.exists())
            appHome.mkdir();

        return appHome.getAbsolutePath();
    }
}
