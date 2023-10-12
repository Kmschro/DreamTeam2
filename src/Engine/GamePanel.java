package Engine;

import GameObject.Rectangle;
import Screens.PlayLevelScreen;
import SpriteFont.SpriteFont;
import Utils.Colors;

import javax.swing.*;

import Game.GameState;
import Game.ScreenCoordinator;

import java.awt.*;

/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel {
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to draw graphics to the panel
	private GraphicsHandler graphicsHandler;

	private boolean isGamePaused = false;
	private SpriteFont pauseLabel;
	private SpriteFont resumeGameLabel;
	private SpriteFont mainMenuLabel;
	private KeyLocker keyLocker = new KeyLocker();
	private final Key pauseKey = Key.ESC;
	private Thread gameLoopProcess;

	//newer variables introduced
	protected int keyPressTimer;
	protected int pointerLocationX, pointerLocationY;
	protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
	protected ScreenCoordinator screenCoordinator;
	protected PlayLevelScreen playLevelScreen;

	private Key showFPSKey = Key.G;
	private SpriteFont fpsDisplayLabel;
	private boolean showFPS = false;
	private int currentFPS;

	// The JPanel and various important class instances are setup here
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		pauseLabel = new SpriteFont("PAUSE", 365, 100, "Comic Sans", 30, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		//code for resume game and main menu labels on pause screen
		resumeGameLabel = new SpriteFont("RESUME GAME", 300, 150, "Comic Sans", 24, Color.white);
        resumeGameLabel.setOutlineColor(Color.black);
        resumeGameLabel.setOutlineThickness(3);
        mainMenuLabel= new SpriteFont("MAIN MENU", 300, 190, "Comic Sans", 24, Color.white);
        mainMenuLabel.setOutlineColor(Color.black);
        mainMenuLabel.setOutlineThickness(3);

		//logic for pasue menu functionailty
		keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);

		fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Comic Sans", 12, Color.black);

		currentFPS = Config.TARGET_FPS;

		// this game loop code will run in a separate thread from the rest of the program
		// will continually update the game's logic and repaint the game's graphics
		GameLoop gameLoop = new GameLoop(this);
		gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());
	}

	// this is called later after instantiation, and will initialize screenManager
	// this had to be done outside of the constructor because it needed to know the JPanel's width and height, which aren't available in the constructor
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	// this starts the timer (the game loop is started here)
	public void startGame() {
		gameLoopProcess.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setCurrentFPS(int currentFPS) {
		this.currentFPS = currentFPS;
	}

	public void update() {
		updatePauseState();
		updateShowFPSState();

		if (!isGamePaused) {
			screenManager.update();
		}

		// //everything here copied from menu screen class
		

		 // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
		 if (Keyboard.isKeyDown(Key.DOWN) &&  keyPressTimer == 0) {
			 keyPressTimer = 14;
			 currentMenuItemHovered++;
		 } else if (Keyboard.isKeyDown(Key.UP) &&  keyPressTimer == 0) {
			 keyPressTimer = 14;
			 currentMenuItemHovered--;
		 } else {
			 if (keyPressTimer > 0) {
				 keyPressTimer--;
			 }
		 }
 
		 // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
		 if (currentMenuItemHovered > 1) {
			 currentMenuItemHovered = 0;
		 } else if (currentMenuItemHovered < 0) {
			 currentMenuItemHovered = 1;
		 }
 
		 // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
		 if (currentMenuItemHovered == 0) {
			 resumeGameLabel.setColor(new Color(255, 215, 0));
			 mainMenuLabel.setColor(new Color(49, 207, 240));
			 pointerLocationX = 270;
			 pointerLocationY = 155;
		 } else if (currentMenuItemHovered == 1) {
			 resumeGameLabel.setColor(new Color(49, 207, 240));
			 mainMenuLabel.setColor(new Color(255, 215, 0));
			 pointerLocationX = 270;
			 pointerLocationY = 195;
		 }
 
		 //if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
		 if (Keyboard.isKeyUp(Key.SPACE)) {
			 keyLocker.unlockKey(Key.SPACE);
		 }
		 if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			 menuItemSelected = currentMenuItemHovered;
			 if (menuItemSelected == 0) {
				 isGamePaused = !isGamePaused;
				 keyLocker.lockKey(Key.SPACE);
			 } else if (menuItemSelected == 1) {
				
			 }
		 }
		
	}

	private void updatePauseState() {
		if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey) && ScreenCoordinator.getGameState() != GameState.MENU && ScreenCoordinator.getGameState() != GameState.CREDITS) {
			isGamePaused = !isGamePaused;
			keyLocker.lockKey(pauseKey);
		}

		if (Keyboard.isKeyUp(pauseKey)) {
			keyLocker.unlockKey(pauseKey);
		}
	}

	private void updateShowFPSState() {
		if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
			showFPS = !showFPS;
			keyLocker.lockKey(showFPSKey);
		}

		if (Keyboard.isKeyUp(showFPSKey)) {
			keyLocker.unlockKey(showFPSKey);
		}

		fpsDisplayLabel.setText("FPS: " + currentFPS);
	}

	public void draw() {
		 screenManager.draw(graphicsHandler);

		// // if game is paused, draw pause gfx over Screen gfx
		if (isGamePaused) {
			pauseLabel.draw(graphicsHandler);
			resumeGameLabel.draw(graphicsHandler);
			mainMenuLabel.draw(graphicsHandler);

			graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
			graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 15, 15, new Color(49, 207, 240), Color.black, 2);
		}

		if (showFPS) {
			fpsDisplayLabel.draw(graphicsHandler);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// every repaint call will schedule this method to be called
		// when called, it will setup the graphics handler and then call this class's draw method
		graphicsHandler.setGraphics((Graphics2D) g);
		draw();
	}
}