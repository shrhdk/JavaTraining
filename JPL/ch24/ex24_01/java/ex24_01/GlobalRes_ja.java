/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_ja extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {GlobalRes.HELLO, "こんにちは"},
            {GlobalRes.GOODBYE, "さようなら"},
    };
}
