import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

/*
    Diese Klasse soll Eigenschaften eines Elements im CircleRain in einer Struktur zusammenfassen.
    Anders als in der ursprünglichen Aufgabe verwenden wir also nicht zwei Arrays, deren Werte sich auf ein logisches Element beziehen.
    Stattdessen wird für jeden Kreis eine Instanz der Klasse Raindrop erstellt.
    Dieses Objekt kann entsprechend manipuliert werden um Größe, Geschwindigkeit und Verkleinerungsrate eines Kreises anzupassen.
    Ein großer Vorteil dieses Ansatzes ist es, dass Werte die logisch zusammengehörig sind, in einer gemeinsamen Einheit liegen.
 */
public class Raindrop {

    /*
        Instanzvariablen eines Regentropfens.
     */
    private float initialRadius;
    private Circle circle;
    private float speed;
    private float decayRate;

    /*
        Konstruktor der Klasse. Die Werte der Übergabeparameter des "new Raindrop(...)" -Aufrufs werden
        in den Instanzvariablen abgelegt.
     */
    public Raindrop(float x, float radius, float speed, float decayRate) {
        this.circle = new Circle(x, 0, radius, Colors.getRandomColor());
        this.speed = speed;
        this.decayRate = decayRate;
        this.initialRadius = radius;
    }

    /*
        In der draw-Methode wird der Kreis des Raindrops gezeichnet.
     */
    public void draw() {
        circle.draw();
    }

    /*
        In der update-Methode wird die Position des Raindrops anhand seiner Geschwindigkeit angepasst.
        Zusätzlich ändert sich anhand der decayRate der Radius. Der Kreis wird entsprechend mit jedem Update kleiner.
     */
    public void update() {
        circle.move(0, speed);
        circle.setRadius(circle.getRadius() * (1 - decayRate));
        checkCanvasBorderCollision();
    }

    /*
        In dieser Methode wird geprüft, ob der Kreis über den Canvas-Rand hinaus bewegt wurde.
        Dann soll er wieder an den oberen Rand gesetzt werden, um einen Regeneffekt zu simulieren, der nicht aufhört.
     */
    private void checkCanvasBorderCollision() {
        if (circle.getYPos() > CircleRain.CANVAS_HEIGHT) {
            resetToCanvasTop();
        }
    }

    /*
        In dieser Methode wird der Kreis zurückgesetzt.
     */
    private void resetToCanvasTop() {
        circle.setYPos(0 - initialRadius);
        circle.setRadius(initialRadius);
    }
}
