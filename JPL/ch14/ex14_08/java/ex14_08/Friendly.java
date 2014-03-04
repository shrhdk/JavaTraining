package ex14_08;

class Friendly {
	private Friendly partner;
	private String name;
	private boolean doYield;

	public Friendly(String name, boolean doYield) {
		this.name = name;
	}

	public synchronized void hug() {
		synchronized (partner) {
			System.out.println(Thread.currentThread().getName() + " in " + name
					+ ".hug() trying to invoke " + partner.name + ".hugBack()");
			if(doYield)
				Thread.yield();
			partner.hugBack();
		}
	}

	private synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name
				+ ".hugBack()");
	}

	public void becomeFriend(Friendly partner) {
		this.partner = partner;
	}

}