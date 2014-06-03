/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_fr_CA extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {GlobalRes.HELLO, "Bonjour"},
            {GlobalRes.GOODBYE, "Au revoir"},
    };
}
