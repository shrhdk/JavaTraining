package ex16_03;

public class ExtendedAttr extends Attr {

	public ExtendedAttr(String name) {
		super(name);
	}
	
	public ExtendedAttr(String name, Object value) {
		super(name, value);
	}
	
	private int rank;
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
}
