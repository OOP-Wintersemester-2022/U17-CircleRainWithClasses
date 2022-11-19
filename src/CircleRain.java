import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class CircleRain extends GraphicsApp {

    /* Private Konstanten */
    public static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private static final int CIRCLE_COUNT = 50;
    private static final int CIRCLE_RADIUS = 20;
    private static final int CIRCLE_WIDTH = CANVAS_WIDTH / CIRCLE_COUNT;
    private static final int MIN_SPEED = 10;
    private static final int MAX_SPEED = 20;
    private static final float MIN_DECAY_RATE = 0.01f;
    private static final float MAX_DECAY_RATE = 0.05f;

    private static final Random rand = new Random();

    private Raindrop[] raindrops;

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */
    @Override
    public void initialize() {
        setupCanvas();
        setupRaindrops();
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */
    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawRaindrops();
    }

    /*
     * In dieser Methode wird die Position aller Raindrops aktualisiert
     * und die Raindrops werden gezeichnet.
     */
    private void drawRaindrops() {
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            raindrops[i].update();
            raindrops[i].draw();
        }
    }

    /*
     * In dieser Methode werden beide Arrays instanziiert und mit Objekten gefüllt.
     * Die Geschwindigkeit und Verkleinerungsrate wird zufällig festgelegt.
     */
    private void setupRaindrops() {
        raindrops = new Raindrop[CIRCLE_COUNT];
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            float speed = rand.nextFloat(MAX_SPEED - MIN_SPEED) + MIN_SPEED;
            float decayRate = rand.nextFloat(MAX_DECAY_RATE - MIN_DECAY_RATE) + MIN_DECAY_RATE;
            raindrops[i] = new Raindrop(
                    CIRCLE_RADIUS + i * CIRCLE_WIDTH,
                    CIRCLE_RADIUS,
                    speed,
                    decayRate
            );
        }
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}