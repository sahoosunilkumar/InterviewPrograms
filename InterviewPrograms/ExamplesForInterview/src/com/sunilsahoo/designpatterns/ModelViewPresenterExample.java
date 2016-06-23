package com.sunilsahoo.designpatterns;

/**
 * 
 * categories: Presentation Tier

## Intent
Apply a "Separation of Concerns" principle in a way that allows
developers to build and test user interfaces.

## Applicability
Use the Model-View-Presenter in any of the following
situations

* when you want to improve the "Separation of Concerns" principle in presentation logic
* when a user interface development and testing is necessary.
* 
* It has been noticed that Android is not able to follow the MVC architecture completely,
* as Activity/Fragment can act as both Controller and view, which makes all the code 
* cluttered at one place. Activity/Fragment can be used to draw multiple views for 
* single screen in app, thus all different data calls and views are populated at the same place.
* Therefore to solve this problem, we can use different design pattern or can implement MVC carefully 
* by taking care of conventions and following proper programming guidelines.
* 
* 
* View:- It renders information to users and contains UI Component .Xml file, Activity, 
* fragments, Dialog comes under View Layer.It do not have any other logic implemented.
Model:- Model here too plays role of domain or business layer and is data source of 
pattern. It describe the main logic of application and decides from where 
the data should be fetched.
Presenter:- This layer performs the functionality of Controller and act as middle layer 
between view and model layer. But unlike controller, it is not much dependent on View. 
View contact presenter for the data to be presented, then Presenter takes data from 
model and returns to view in presentable format. Presenter is a simple java class 
that do not contain any UI components, it just manipulates data from model and displays 
it on view.
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModelViewPresenterExample {
	public static void main(String[] args) {
		FileLoader loader = new FileLoader();
		FileSelectorJFrame jFrame = new FileSelectorJFrame();
		FileSelectorPresenter presenter = new FileSelectorPresenter(jFrame);
		presenter.setLoader(loader);
		presenter.start();
	}
}

class FileLoader {

	/**
	 * Indicates if the file is loaded or not.
	 */
	private boolean loaded;

	/**
	 * The name of the file that we want to load.
	 */
	private String fileName;

	/**
	 * Loads the data of the file specified.
	 */
	public String loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.fileName)));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}

			this.loaded = true;
			br.close();

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Sets the path of the file to be loaded, to the given value.
	 * 
	 * @param fileName
	 *            The path of the file to be loaded.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return fileName The path of the file to be loaded.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @return True, if the file given exists, false otherwise.
	 */
	public boolean fileExists() {
		return new File(this.fileName).exists();
	}

	/**
	 * @return True, if the file is loaded, false otherwise.
	 */
	public boolean isLoaded() {
		return this.loaded;
	}
}

/**
 * This class is the GUI implementation of the View component in the
 * Model-View-Presenter pattern.
 */
class FileSelectorJFrame extends JFrame implements FileSelectorView, ActionListener {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The "OK" button for loading the file.
	 */
	private JButton ok;

	/**
	 * The cancel button.
	 */
	private JButton cancel;

	/**
	 * The information label.
	 */
	private JLabel info;

	/**
	 * The contents label.
	 */
	private JLabel contents;

	/**
	 * The text field for giving the name of the file that we want to open.
	 */
	private JTextField input;

	/**
	 * A text area that will keep the contents of the file opened.
	 */
	private JTextArea area;

	/**
	 * The panel that will hold our widgets.
	 */
	private JPanel panel;

	/**
	 * The Presenter component that the frame will interact with
	 */
	private FileSelectorPresenter presenter;

	/**
	 * The name of the file that we want to read it's contents.
	 */
	private String fileName;

	/**
	 * Constructor.
	 */
	public FileSelectorJFrame() {
		super("File Loader");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(100, 100, 500, 200);

		/*
		 * Add the panel.
		 */
		this.panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		panel.setBounds(0, 0, 500, 200);
		panel.setBackground(Color.LIGHT_GRAY);

		/*
		 * Add the info label.
		 */
		this.info = new JLabel("File Name :");
		this.panel.add(info);
		info.setBounds(30, 10, 100, 30);

		/*
		 * Add the contents label.
		 */
		this.contents = new JLabel("File contents :");
		this.panel.add(contents);
		this.contents.setBounds(30, 100, 120, 30);

		/*
		 * Add the text field.
		 */
		this.input = new JTextField(100);
		this.panel.add(input);
		this.input.setBounds(150, 15, 200, 20);

		/*
		 * Add the text area.
		 */
		this.area = new JTextArea(100, 100);
		JScrollPane pane = new JScrollPane(area);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.panel.add(pane);
		this.area.setEditable(false);
		pane.setBounds(150, 100, 250, 80);

		/*
		 * Add the OK button.
		 */
		this.ok = new JButton("OK");
		this.panel.add(ok);
		this.ok.setBounds(250, 50, 100, 25);
		this.ok.addActionListener(this);

		/*
		 * Add the cancel button.
		 */
		this.cancel = new JButton("Cancel");
		this.panel.add(this.cancel);
		this.cancel.setBounds(380, 50, 100, 25);
		this.cancel.addActionListener(this);

		this.presenter = null;
		this.fileName = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.ok.equals(e.getSource())) {
			this.fileName = this.input.getText();
			presenter.fileNameChanged();
			presenter.confirmed();
		} else if (this.cancel.equals(e.getSource())) {
			presenter.cancelled();
		}
	}

	@Override
	public void open() {
		this.setVisible(true);
	}

	@Override
	public void close() {
		this.dispose();
	}

	@Override
	public boolean isOpened() {
		return this.isVisible();
	}

	@Override
	public void setPresenter(FileSelectorPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public FileSelectorPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void setFileName(String name) {
		this.fileName = name;
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public void displayData(String data) {
		this.area.setText(data);
	}
}

/**
 * Every instance of this class represents the Presenter component in the
 * Model-View-Presenter architectural pattern.
 * <p>
 * It is responsible for reacting to the user's actions and update the View
 * component.
 */
class FileSelectorPresenter {

	/**
	 * The View component that the presenter interacts with.
	 */
	private FileSelectorView view;

	/**
	 * The Model component that the presenter interacts with.
	 */
	private FileLoader loader;

	/**
	 * Constructor
	 * 
	 * @param view
	 *            The view component that the presenter will interact with.
	 */
	public FileSelectorPresenter(FileSelectorView view) {
		this.view = view;
	}

	/**
	 * Sets the {@link FileLoader} object, to the value given as parameter.
	 * 
	 * @param loader
	 *            The new {@link FileLoader} object(the Model component).
	 */
	public void setLoader(FileLoader loader) {
		this.loader = loader;
	}

	/**
	 * Starts the presenter.
	 */
	public void start() {
		view.setPresenter(this);
		view.open();
	}

	/**
	 * An "event" that fires when the name of the file to be loaded changes.
	 */
	public void fileNameChanged() {
		loader.setFileName(view.getFileName());
	}

	/**
	 * Ok button handler
	 */
	public void confirmed() {
		if (loader.getFileName() == null || loader.getFileName().equals("")) {
			view.showMessage("Please give the name of the file first!");
			return;
		}

		if (loader.fileExists()) {
			String data = loader.loadData();
			view.displayData(data);
		} else {
			view.showMessage("The file specified does not exist.");
		}
	}

	/**
	 * Cancels the file loading process.
	 */
	public void cancelled() {
		view.close();
	}
}

/**
 * Every instance of this class represents the Stub component in the
 * Model-View-Presenter architectural pattern.
 * <p>
 * The stub implements the View interface and it is useful when we want the test
 * the reaction to user events, such as mouse clicks.
 * <p>
 * Since we can not test the GUI directly, the MVP pattern provides this
 * functionality through the View's dummy implementation, the Stub.
 */
class FileSelectorStub implements FileSelectorView {

	/**
	 * Indicates whether or not the view is opened.
	 */
	private boolean opened;

	/**
	 * The presenter Component.
	 */
	private FileSelectorPresenter presenter;

	/**
	 * The current name of the file.
	 */
	private String name;

	/**
	 * Indicates the number of messages that were "displayed" to the user.
	 */
	private int numOfMessageSent;

	/**
	 * Indicates if the data of the file where displayed or not.
	 */
	private boolean dataDisplayed;

	/**
	 * Constructor
	 */
	public FileSelectorStub() {
		this.opened = false;
		this.presenter = null;
		this.name = "";
		this.numOfMessageSent = 0;
		this.dataDisplayed = false;
	}

	@Override
	public void open() {
		this.opened = true;
	}

	@Override
	public void setPresenter(FileSelectorPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public boolean isOpened() {
		return this.opened;
	}

	@Override
	public FileSelectorPresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public String getFileName() {
		return this.name;
	}

	@Override
	public void setFileName(String name) {
		this.name = name;
	}

	@Override
	public void showMessage(String message) {
		this.numOfMessageSent++;
	}

	@Override
	public void close() {
		this.opened = false;
	}

	@Override
	public void displayData(String data) {
		this.dataDisplayed = true;
	}

	/**
	 * Returns the number of messages that were displayed to the user.
	 */
	public int getMessagesSent() {
		return this.numOfMessageSent;
	}

	/**
	 * @return True if the data where displayed, false otherwise.
	 */
	public boolean dataDisplayed() {
		return this.dataDisplayed;
	}
}

/**
 * This interface represents the View component in the Model-View-Presenter
 * pattern. It can be implemented by either the GUI components, or by the Stub.
 */
interface FileSelectorView {

	/**
	 * Opens the view.
	 */
	void open();

	/**
	 * Closes the view.
	 */
	void close();

	/**
	 * @return True, if the view is opened, false otherwise.
	 */
	boolean isOpened();

	/**
	 * Sets the presenter component, to the one given as parameter.
	 * 
	 * @param presenter
	 *            The new presenter component.
	 */
	void setPresenter(FileSelectorPresenter presenter);

	/**
	 * @return The presenter Component.
	 */
	FileSelectorPresenter getPresenter();

	/**
	 * Sets the file's name, to the value given as parameter.
	 * 
	 * @param name
	 *            The new name of the file.
	 */
	void setFileName(String name);

	/**
	 * @return The name of the file.
	 */
	String getFileName();

	/**
	 * Displays a message to the users.
	 * 
	 * @param message
	 *            The message to be displayed.
	 */
	void showMessage(String message);

	/**
	 * Displays the data to the view.
	 * 
	 * @param data
	 *            The data to be written.
	 */
	void displayData(String data);
}
