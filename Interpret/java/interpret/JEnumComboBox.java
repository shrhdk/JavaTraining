package interpret;

import javax.swing.*;

public class JEnumComboBox extends JComboBox {

    private final Enum<?>[] enumConstants;

    private Enum<?> selectedItem;

    public JEnumComboBox(Class<? extends Enum<?>> type, Enum<?> initialValue) {
        enumConstants = type.getEnumConstants();

        selectedItem = initialValue;

        setModel(new EnumListModel());
    }

    private class EnumListModel extends AbstractListModel implements ComboBoxModel {
        @Override
        public int getSize() {
            // +1 is for null
            return enumConstants.length + 1;
        }

        @Override
        public Object getElementAt(int i) {
            if (i == 0) {
                return null;
            } else {
                return enumConstants[i - 1];
            }
        }

        @Override
        public void setSelectedItem(Object value) {
            selectedItem = (Enum<?>) value;
        }

        @Override
        public Object getSelectedItem() {
            return selectedItem;
        }
    }
}
