package jpl.chapter2.exercise4;

public class Answer {

	public static void main(String[] args) {
		System.out.println("はい。finalにすべきです。一般的に、識別子は名前とは違って変更不可能であるべきです。"
				+ "finalにしておくことで、privateメンバーから変更される危険性もなくなります。"
				+ "複製やシリアライズをされることは想定していません。");
	}

}
