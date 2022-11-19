import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class CircleRain extends GraphicsApp {

    /* Private Konstanten */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private static final int CIRCLE_COUNT = 50;
    private static final int CIRCLE_WIDTH = CANVAS_WIDTH/CIRCLE_COUNT;
    private static final int CIRCLE_RADIUS = CIRCLE_WIDTH/2;
    private static final int MAX_SPEED = 10;

    private static final Random rand = new Random();

    private Circle[] circles;
    private float[] circleSpeeds;

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupCanvas();
        setupCirclesAndSpeeds();
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawCircles();
    }

    /*
     * In dieser Methode wird die Position aller Kreise aktualisiert
     * und die Kreise werden gezeichnet.
     */
    private void drawCircles() {
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            updateCircle(circles[i], circleSpeeds[i]);
            circles[i].draw();
        }
    }

    /*
     * Bei der Kreisaktualisierung wird überprüft, ob der Kreis am unteren Rand
     * anstößt. Wenn dies der Fall ist, wird er wieder auf seine Startposition gesetzt.
     */
    private void updateCircle(Circle circle, float speed) {
        circle.move(0, speed);
        if (circle.getYPos() > CANVAS_HEIGHT) {
            circle.setYPos(CIRCLE_RADIUS);
        }
    }

    /*
     * In dieser Methode werden beide Arrays instanziiert und mit Objekten gefüllt.
     * Die Geschwindigkeit wird zufällig festgelegt.
     */
    private void setupCirclesAndSpeeds() {
        circleSpeeds = new float[CIRCLE_COUNT];
        circles = new Circle[CIRCLE_COUNT];

        for (int i = 0; i < CIRCLE_COUNT; i++) {
            circles[i] = new Circle(CIRCLE_RADIUS + i * CIRCLE_WIDTH, CIRCLE_RADIUS, CIRCLE_RADIUS, Colors.getRandomColor());
            circleSpeeds[i] = rand.nextFloat() * MAX_SPEED;
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
