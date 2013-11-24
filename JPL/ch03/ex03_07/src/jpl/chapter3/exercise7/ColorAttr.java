package jpl.chapter3.exercise7;

class ColorAttr extends Attr {

	ScreenColor myColor; // �ϊ����ꂽ�F

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name) {
		this(name, "transparent");
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	@Override
	public Object setValue(Object newValue) {
		// �X�[�p�[�N���X��setValue���ŏ��ɍs��
		Object oldValue = super.setValue(newValue);
		decodeColor();
		return oldValue;
	}

	/** �l���L�q�ł͂Ȃ�ScreenColor�ɐݒ肷�� */
	public ScreenColor setValue(ScreenColor newValue) {
		// �X�[�p�[�N���X��setValue���ŏ��ɍs��
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/** �ϊ����ꂽScreenColor�I�u�W�F�N�g��Ԃ� */
	public ScreenColor getColor() {
		return myColor;
	}

	/** getValue()�œ�����L�q����ScreenColor��ݒ肷�� */
	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	public static class ScreenColor {

		public ScreenColor(Object value) {
		}

		@Override
		public String toString() {
			return null;
		}

	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ColorAttr) {
			ColorAttr colorAttr = (ColorAttr)obj;
			String colorAttrValue = (String)colorAttr.getValue();
			// return true when both of Name and Value are equal.
			return getName().equals(colorAttr.getName()) && ((String)getValue()).equals(colorAttrValue);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getName().hashCode() * getValue().hashCode();
	}
}
