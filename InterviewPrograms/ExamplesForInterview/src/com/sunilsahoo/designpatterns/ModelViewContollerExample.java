package com.sunilsahoo.designpatterns;

/**
 * categories: Presentation Tier
 * 
 * ## Intent Separate the user interface into three interconnected components:
 * the model, the view and the controller. Let the model manage the data, the
 * view display the data and the controller mediate updating the data and
 * redrawing the display.
 * 
 * ## Applicability Use the Model-View-Controller pattern when
 * 
 * you want to clearly separate the domain data from its user interface
 * representation
 * 
 * @author sunilkumarsahoo
 *
 */
public class ModelViewContollerExample {
	public static void main(String[] args) {
		// create model, view and controller
		GiantModel giant = new GiantModel(Health.HEALTHY, Fatigue.ALERT,
				Nourishment.SATURATED);
		GiantView view = new GiantView();
		GiantController controller = new GiantController(giant, view);
		// initial display
		controller.updateView();
		// controller receives some interactions that affect the giant
		controller.setHealth(Health.WOUNDED);
		controller.setNourishment(Nourishment.HUNGRY);
		controller.setFatigue(Fatigue.TIRED);
		// redisplay
		controller.updateView();
	}
}

enum Fatigue {

	ALERT("alert"), TIRED("tired"), SLEEPING("sleeping");

	private String title;

	Fatigue(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}

enum Health {

	HEALTHY("healthy"), WOUNDED("wounded"), DEAD("dead");

	private String title;

	Health(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}

enum Nourishment {

	SATURATED("saturated"), HUNGRY("hungry"), STARVING("starving");

	private String title;

	Nourishment(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}

class GiantController {

	private GiantModel giant;
	private GiantView view;

	public GiantController(GiantModel giant, GiantView view) {
		this.giant = giant;
		this.view = view;
	}

	public Health getHealth() {
		return giant.getHealth();
	}

	public void setHealth(Health health) {
		this.giant.setHealth(health);
	}

	public Fatigue getFatigue() {
		return giant.getFatigue();
	}

	public void setFatigue(Fatigue fatigue) {
		this.giant.setFatigue(fatigue);
	}

	public Nourishment getNourishment() {
		return giant.getNourishment();
	}

	public void setNourishment(Nourishment nourishment) {
		this.giant.setNourishment(nourishment);
	}

	public void updateView() {
		this.view.displayGiant(giant);
	}
}

class GiantModel {

	private Health health;
	private Fatigue fatigue;
	private Nourishment nourishment;

	GiantModel(Health health, Fatigue fatigue, Nourishment nourishment) {
		this.health = health;
		this.fatigue = fatigue;
		this.nourishment = nourishment;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public Fatigue getFatigue() {
		return fatigue;
	}

	public void setFatigue(Fatigue fatigue) {
		this.fatigue = fatigue;
	}

	public Nourishment getNourishment() {
		return nourishment;
	}

	public void setNourishment(Nourishment nourishment) {
		this.nourishment = nourishment;
	}

	@Override
	public String toString() {
		return String.format("The giant looks %s, %s and %s.", health, fatigue,
				nourishment);
	}
}

class GiantView {

	public void displayGiant(GiantModel giant) {
		System.out.println(giant);
	}
}
