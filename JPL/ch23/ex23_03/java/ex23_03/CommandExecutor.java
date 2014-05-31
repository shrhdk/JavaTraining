/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex23_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandExecutor {

    public static final String EXIT_STRING = "EXIT";

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
            if(EXIT_STRING.equals(line)) {
                process.destroy();
                break;
            }
        }

        return process;
    }
}
