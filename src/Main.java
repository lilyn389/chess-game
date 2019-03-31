
public class Main {
	
	private static Main instance;
	private Data data;
	private Graphics graphics;
	
	public Main() {
		instance = this;
		this.data = new Data();
		this.graphics = new Graphics("Chess", 800, 800);
	}
	
	//for use with changing screens on button presses in the Menu subclasses
	public static Main getInstance() {
		return instance;
	}
	
	public void switchScreen(MenuType newMenu) {
		this.graphics.showMenu(newMenu);
	}
	
	public static void main(String[] args) {
		//create a new graphics instance, this will start the GUI
		new Main();
		
		//start the game on the splash screen
		Main.getInstance().graphics.showMenu(MenuType.Splash);
	}

}
