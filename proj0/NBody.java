public class NBody {

    private static String starfieldFile = "images/starfield.jpg";
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[numberOfPlanets];
        for(int i = 0; i < numberOfPlanets; i++){
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }

        return planets;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        double time = .0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.picture(0, 0, starfieldFile);
        for(Planet p : planets){
            p.imgFileName = "images/" + p.imgFileName;
            p.draw();
        }

        while(time < T){
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, starfieldFile);
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        for(Planet p : planets){
            p.imgFileName =p.imgFileName.substring(7);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);}

    }
}
