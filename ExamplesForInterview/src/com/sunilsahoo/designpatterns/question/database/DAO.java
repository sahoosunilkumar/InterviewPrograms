package com.sunilsahoo.designpatterns.question.database;



public class DAO extends DatabaseQueryAbs implements IDAO{
	
	/**
	   * Static to class instance of the class.
	   */
	  private static final DAO INSTANCE = new DAO();

	  /**
	   * Private constructor so nobody can instantiate the class.
	   */
	  private DAO() {}

	  /**
	   * To be called by user to obtain instance of the class.
	   *
	   * @return instance of the singleton.
	   */
	  public static DAO getInstance() {
	    return INSTANCE;
	  }

	@Override
	public double getItem() {
		// TODO Auto-generated method stub
		String query = "sql statement to getItem";
		execute(query);
		return 0;
	}

	@Override
	public void setTotal(double totalPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void query(String query) {
		System.out.println(" perform other stuff related to building and creating query");
		// TODO Auto-generated method stub
		
	}

}
