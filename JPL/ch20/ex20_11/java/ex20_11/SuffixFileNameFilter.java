/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFileNameFilter implements FilenameFilter {

    private final String suffix;

    public SuffixFileNameFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}
