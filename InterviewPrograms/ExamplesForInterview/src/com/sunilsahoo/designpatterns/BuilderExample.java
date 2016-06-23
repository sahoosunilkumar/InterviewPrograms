package com.sunilsahoo.designpatterns;

import com.sunilsahoo.designpatterns.Party.PartyBuilder;

/**
 * 
 * Type: Creational
 * 
 * ## Intent Separate the construction of a complex object from its
 * representation so that the same construction process can create different
 * representations.
 * 
 * 
 * ## Applicability Use the Builder pattern when
 * 
 * the algorithm for creating a complex object should be independent of the
 * parts that make up the object and how they're assembled the construction
 * process must allow different representations for the object that's
 * constructed
 * 
 * 
 * Builder pattern builds a complex object using simple objects and using a step
 * by step approach. This type of design pattern comes under creational pattern
 * as this pattern provides one of the best ways to create an object. A Builder
 * class builds the final object step by step. This builder is independent of
 * other objects.
 * 
 * Example StringBuilder, AlertBuilder, NotificationBuilder
 * @author sunilkumarsahoo
 *
 */
public class BuilderExample {
	public static void main(String args[]) {
		Party party1 = new PartyBuilder("Meeting", "CCD").build();
		System.out.println(" party1 details = " + party1);
		party1 = new PartyBuilder("Team Lunch", "Spice Garden").withmaincourse("potato chips")
				.withmaincourse("mutton biryani").build();
		System.out.println(" party2 details = " + party1);
		party1 = new PartyBuilder("Team Outing", "Wayanad").withmaincourse("Sandwich").withmaincourse("chicken biryani")
				.withdesert("ice cream").withdanceType("HipHop").build();
		System.out.println(" party3 details = " + party1);
	}
}

/**
 * 
 * Party, the class with many parameters.
 * 
 */
final class Party {

	private final String place;
	private final String name;
	private final String starter;
	private final String maincourse;
	private final String desert;
	private final String danceType;

	private Party(PartyBuilder builder) {
		this.place = builder.place;
		this.name = builder.name;
		this.maincourse = builder.maincourse;
		this.starter = builder.starter;
		this.danceType = builder.danceType;
		this.desert = builder.desert;
	}

	public String getplace() {
		return place;
	}

	public String getName() {
		return name;
	}

	public String getstarter() {
		return starter;
	}

	public String getmaincourse() {
		return maincourse;
	}

	public String getdesert() {
		return desert;
	}

	public String getdanceType() {
		return danceType;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(" Party : ").append(name).append(" at ").append(place);
		if (danceType != null) {
			sb.append(" lets dance on " + danceType);
		}
		if (starter != null) {
			sb.append(" start with " + starter);
		}
		if (maincourse != null) {
			sb.append(" followed by ").append(maincourse);
		}
		if (desert != null) {
			sb.append(" ends with ").append(desert);
		}

		return sb.toString();
	}

	/**
	 * 
	 * The builder class.
	 * 
	 */
	public static class PartyBuilder {

		private final String place;
		private final String name;
		private String starter;
		private String maincourse;
		private String desert;
		private String danceType;

		/**
		 * Constructor
		 */
		public PartyBuilder(String name, String place) {
			if (place == null || name == null) {
				throw new IllegalArgumentException("place and name can not be null");
			}
			this.place = place;
			this.name = name;
		}

		public PartyBuilder withstarter(String starter) {
			this.starter = starter;
			return this;
		}

		public PartyBuilder withmaincourse(String maincourse) {
			this.maincourse = maincourse;
			return this;
		}

		public PartyBuilder withdesert(String desert) {
			this.desert = desert;
			return this;
		}

		public PartyBuilder withdanceType(String danceType) {
			this.danceType = danceType;
			return this;
		}

		public Party build() {
			return new Party(this);
		}
	}
}
