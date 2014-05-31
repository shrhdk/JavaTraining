/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommandExecutor {

    public static void main(String[] args) {
        try {
            Process process = userProg("test/hello_x.rb");
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Process userProg(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, process.getOutputStream());
        plugTogether(System.out, process.getInputStream());
        plugTogether(System.err, process.getErrorStream());
        return process;
    }

    private static void plugTogether(final InputStream src, final OutputStream dst) {
        Runnable runnable = () -> {
            for (; ; ) {
                try {
                    int data;
                    if ((data = src.read()) != -1) {
                        dst.write(data);
                        dst.flush();
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    private static void plugTogether(OutputStream dst, InputStream src) {
        plugTogether(src, dst);
    }
}
