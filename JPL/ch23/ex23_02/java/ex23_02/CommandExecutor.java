/*
 * Copyright(C) 2014 Hideki Shiro
 */
package ex23_02;

import java.io.*;

public class CommandExecutor {
    public static void main(String[] args) {
        try {
            Process process = exec(args);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Process exec(String[] cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        int lineNumber = 1;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(lineNumber++ + ": " + line);
        }

        return process;
    }
}
