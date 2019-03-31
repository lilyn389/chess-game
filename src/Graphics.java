import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

//this is the master panel, is controlled by a card layout, when the card layout switches cards the screen changes
public class Graphics extends JPanel {
	//private static final long serialVersionUID = 1L;
	
	private final int WINDOW_WIDTH;
	private final int WINDOW_HEIGHT;
	
	private JFrame frame;
	private CardLayout cLayout;
	
	private SplashMenu splashMenu;
	private InitializationMenu initializationMenu;
	private GameMenu gameMenu;
	
	public Graphics(String gameTitle, int windowWidth, int windowHeight) {
		this.WINDOW_WIDTH = windowWidth;
		this.WINDOW_HEIGHT = windowHeight;
		
		//create a new JFrame, set this class (Graphics.java) as the main container of the screen (this class is a JPanel)
		this.frame = new JFrame(gameTitle);
		this.frame.getContentPane().add(this);
		this.frame.pack();
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		
		this.cLayout = new CardLayout();
		this.setLayout(this.cLayout);
		
		createMenus();
	}
	
	//CardLayout obeys this set size variant, this code just sets the size of the window to be 800x600 (from Main.java)
	public Dimension getPreferredSize() {
		return new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
	}

	//creates a Menu object for each menu, adds that Menu object to the card layout
	private void createMenus() {
		//create a new instance of each type of menu so that they can be added later to the master panel (Graphics.java)
		this.splashMenu = new SplashMenu();
		this.initializationMenu = new InitializationMenu();
		this.gameMenu = new GameMenu();
		
		//add each above instance to the master panel, giving each a name so that they can be displayed in showMenu() below
		this.add(splashMenu, SplashMenu.getMenuName());
		this.add(initializationMenu, InitializationMenu.getMenuName());
        this.add(gameMenu, GameMenu.getMenuName());
	}
	
	//switches the card layout to show one of the menus added to this (Graphics.java) panel,
	//shows this menu on the screen (and hides the old menu)
	public void showMenu(MenuType menuToShow) {
		resetMenu(menuToShow);
		CardLayout cLayout = (CardLayout) this.getLayout();
		cLayout.show(this, getMenuName(menuToShow));
	}
	
	//resets the specified menu to get it ready to be shown again
	private void resetMenu(MenuType menuToShow) {
		switch (menuToShow) {
		case Splash:
			this.splashMenu.reset();
			break;
		case Initialization:
			this.initializationMenu.reset();
			break;
		case Game:
			this.gameMenu.reset();
			break;
		}
	}
	
	//for use in showMenu() above only, returns the name of a Menu given a MenuType (enum)
	private String getMenuName(MenuType menuToShow) {
		switch (menuToShow) {
		case Splash:
			return SplashMenu.getMenuName();
		case Initialization:
			return InitializationMenu.getMenuName();
		case Game:
			return GameMenu.getMenuName();
		}
	}
	
}
