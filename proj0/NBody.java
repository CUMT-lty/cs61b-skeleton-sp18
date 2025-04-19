import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 * simulate a universe
 */
public class NBody {

    /**
     * return the corresponding radius of the universe
     */
    public static double readRadius(String filepath) {
        In in = new In(filepath); // Path to relative root directory of the project
        in.readInt();
        return in.readDouble();
    }

    /**
     * return an array of Planets corresponding to the planets in the file
     */
    public static Planet[] readPlanets(String filepath) {
        In in = new In(filepath);
        int n = in.readInt();
        in.readDouble();
        Planet[] planetArray = new Planet[n];
        for (int i = 0; i < n; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planetArray[i] = planet;
        }
        return planetArray;
    }

    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planetArray = readPlanets(filename);

        // for Creating Animation
        StdDraw.enableDoubleBuffering();

        // Drawing the Background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        // Drawing All the Planets
        Arrays.stream(planetArray).forEach(Planet::draw);
        // show the draw
        StdDraw.show();
        // Creating an Animation
        double t = 0.0;
        while (t != T) {
            double[] xForces = new double[planetArray.length];
            double[] yForces = new double[planetArray.length];
            for (int i = 0; i < planetArray.length; i++) {
                xForces[i] = planetArray[i].calcNetForceExertedByX(planetArray);
                yForces[i] = planetArray[i].calcNetForceExertedByY(planetArray);
            }
            for (int i = 0; i < planetArray.length; i++) {
                planetArray[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            Arrays.stream(planetArray).forEach(Planet::draw);
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        // Printing the Universe when the final time T have been reached
        StdOut.printf("%d\n", planetArray.length);
        StdOut.printf("%.2e\n", radius);
        Arrays.stream(planetArray).forEach(p -> {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        });
    }
}
