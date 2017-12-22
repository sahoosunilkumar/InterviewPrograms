package com.sunilsahoo.designpatterns;

/**
 * Type: Structural Intent : • Attach additional responsibilities to an object
 * dynamically. Decorators provide a flexible alternative to subclassing for
 * extending functionality. • Client-specified embellishment of a core object by
 * recursively wrapping it. • Wrapping a gift, putting it in a box, and wrapping
 * the box.
 * 
 * Problem : You want to add behavior or state to individual objects at
 * run-time. Inheritance is not feasible because it is static and applies to an
 * entire class.
 * 
 * @author sunilkumarsahoo
 *
 */
public class DecoratorExample {

	interface Offer {
		void apply();
	}

	static class PromoCodeOffer implements Offer {
		@Override
		public void apply() {
			System.out.print("PromoCodeOffer");
		}
	}

	static class PremiumCustomerOffer implements Offer {
		private Offer core;

		public PremiumCustomerOffer(Offer inner) {
			core = inner;
		}

		@Override
		public void apply() {
			core.apply();
			doX();
		}

		private void doX() {
			System.out.print("+PremiumCustomerOffer");
		}
	}

	static abstract class CardOffer implements Offer {
		private Offer core;

		public CardOffer(Offer inner) {
			core = inner;
		}

		@Override
		public void apply() {
			core.apply();
		}
	}

	static class HDFCCardOffer extends CardOffer {
		public HDFCCardOffer(Offer inner) {
			super(inner);
		}

		@Override
		public void apply() {
			super.apply();
			doX();
		}

		private void doX() {
			System.out.print("+HDFCCardOffer");
		}
	}

	static class ClubCardOffer extends CardOffer {
		public ClubCardOffer(Offer inner) {
			super(inner);
		}

		@Override
		public void apply() {
			super.apply();
			doY();
		}

		private void doY() {
			System.out.print("+ClubCardOffer");
		}
	}

	public static void main(String[] args) {
		Offer[] array = { new HDFCCardOffer(new PromoCodeOffer()),
				new ClubCardOffer(new HDFCCardOffer(new PromoCodeOffer())),
				new PremiumCustomerOffer(new ClubCardOffer(
						new HDFCCardOffer(new PromoCodeOffer()))) };
		for (int i = 0; i < array.length; i++) {
			System.out.print(
					"Available Offer for customer " + (i + 1) + " is : ");
			array[i].apply();
			System.out.println();
		}
	}
}
