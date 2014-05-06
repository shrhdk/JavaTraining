/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_08;

public class Section {
    private final String header;
    private final long begin;
    private final long end;

    public Section(String header, long begin, long end) {
        this.header = header;
        this.begin = begin;
        this.end = end;
    }

    public String getHeader() {
        return header;
    }

    public long getBegin() {
        return begin;
    }

    public long getEnd() {
        return end;
    }
}
