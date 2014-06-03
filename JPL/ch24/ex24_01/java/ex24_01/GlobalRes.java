/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes extends ListResourceBundle {
    public static final String HELLO = "hello";
    public static final String GOODBYE = "goodbye";

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {GlobalRes.HELLO, "Ciao"},
            {GlobalRes.GOODBYE, "Ciao"},
    };
}
