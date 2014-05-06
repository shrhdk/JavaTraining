/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_09;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileInfo {

    public static void showFileInfo(String... filepathes) throws IOException {
        for(String filePath : filepathes) {
            showFileInfo(filePath);
        }
    }

    public static void showFileInfo(String filePath) throws IOException {
        File file = new File(filePath);

        System.out.println("=== " + filePath + " ===");

        System.out.println("          Name: " + file.getName());
        System.out.println("          Path: " + file.getPath());
        System.out.println(" Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("        Parent: " + file.getParent());

        System.out.println("        Exists: " + file.exists());
        System.out.println("      Can Read: " + file.canRead());
        System.out.println("     Can Write: " + file.canWrite());
        System.out.println("   Can Execute: " + file.canExecute());
        System.out.println("       is File: " + file.isFile());
        System.out.println("  is Directory: " + file.isDirectory());
        System.out.println("   is Absolute: " + file.isAbsolute());
        System.out.println("     is Hidden: " + file.isHidden());

        System.out.println(" Last Modified: " + new Date(file.lastModified()));
        System.out.println("        Length: " + file.length() + "Byte");

        System.out.println();
    }
}
