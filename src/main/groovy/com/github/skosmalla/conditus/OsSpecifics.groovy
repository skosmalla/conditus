package com.github.skosmalla.conditus

import com.github.skosmalla.conditus.exception.OperationSystemNotSupportedException

/**
 * @author skosmalla
 * @since 09.06.14
 */
enum OsSpecifics {
    WIN,
    UNIX

    public String getCommandLinePrefix() {
        if (this == WIN) {
            return "cmd /c "
        }

        if (this == UNIX) {
            return ""
        }

        null
    }

    private static OsSpecifics osName;

    static OsSpecifics getOsName() {
        if (osName == null) {
            osName = validateOs()
        }
        osName
    }

    private static OsSpecifics validateOs() {
        def osName = System.getProperty("os.name").toLowerCase()

        if (isWindows(osName)) {
            return WIN
        } else if (isUnix(osName)) {
            return UNIX
        }

        throw new OperationSystemNotSupportedException()
    }

    private static boolean isUnix(String osName) {
        osName.contains("nix") || osName.contains("nux") || osName.contains("aix")
    }

    private static boolean isWindows(String osName) {
        osName.contains("win")
    }
}
