package jpl.chapter5.exercise2;

import java.util.ArrayList;

public class BankAccount {
	private static long count = 0;

	public static void resetCount() {
		count = 0;
	}

	public final long number = count++;
	private long balance = 0;
	private Action lastAct;
	private History history = new History();

	public long showBalance() {
		return balance;
	}

	public void deposit(long amount) {
		balance += amount;
		lastAct = this.new Action("deposit", amount);
		history.add(lastAct);
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = this.new Action("withdraw", amount);
		history.add(lastAct);
	}

	public void transfer(BankAccount other, long amount) {
		other.withdraw(amount);
		this.deposit(amount);
	}

	public History history() {
		return history;
	}

	public class Action {
		private String act;
		private long amount;

		private Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		public String toString() {
			// identify our enclosing account
			return number + ": " + act + " " + amount;
		}
	}

	// BankAccount�̂��߂̃N���X�Ȃ̂ŁA�����N���X�ɂ���ׂ��ł���B
	// �܂�History��BankAccount��private�ȃC���X�^���X�����o�[�ɃA�N�Z�X����K�v���Ȃ��̂ŁA
	// static�ł悢�B
	public static class History {

		int index = 0;
		ArrayList<Action> history = new ArrayList<Action>();

		private History() {
		}

		protected void add(Action action) {
			if (0 <= history.size() && history.size() < 10) {
				history.add(action);
			} else if (history.size() == 10) {
				history.remove(0);
				history.add(action);
			}
		}

		protected boolean hasNext() {
			if (0 <= index && index < history.size() - 1)
				return true;
			else if (index == history.size()) {
				return false;
			} else {
				throw new AssertionError(String.format(
						"Index is %d and history size is %d", index,
						history.size()));
			}
		}

		public Action next() {
			if (0 <= index && index < history.size())
				return history.get(index++);
			else if (index == history.size()) {
				return null;
			} else {
				throw new AssertionError(String.format(
						"Index is %d and history size is %d", index,
						history.size()));
			}
		}
	}
}
