import java.awt.*;
import java.util.Arrays;

/**
 * Planet in universe
 */
public class Planet {

    // position
    public double xxPos;
    public double yyPos;
    // velocity
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // definition of constants
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**
     * calculate the distance between two planets
     */
    public double calcDistance(Planet p) {
        double dx = Math.abs(this.xxPos - p.xxPos);
        double dy = Math.abs(this.yyPos - p.yyPos);
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * calculate the force between two planets
     */
    public double calcForceExertedBy(Planet p) {
        return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }

    /**
     * calculate the X-components force between two planets on the
     */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
    }

    /**
     * calculate the Y-components force between two planets on the
     */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
    }

    /**
     * calculate the net X force exerted by all planets in that array upon the current Planet
     */
    public double calcNetForceExertedByX(Planet[] pArray) {
         return Arrays.stream(pArray)
                .filter(p -> !this.equals(p)) // ignore any planet in pArray that is equal to the current planet
                .map(this::calcForceExertedByX)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    /**
     * calculate the net Y force exerted by all planets in that array upon the current Planet
     */
    public double calcNetForceExertedByY(Planet[] pArray) {
        return Arrays.stream(pArray)
                .filter(p -> !this.equals(p)) // ignore any planet in pArray that is equal to the current planet
                .map(this::calcForceExertedByY)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    /**
     * change in the planet’s velocity and position by force in a small period of time dt
     */
    public void update(double dt, double xforce, double yforce) {
        double ax = xforce / this.mass;
        double ay = yforce / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /**
     * a planet draw itself at its appropriate position
     */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
