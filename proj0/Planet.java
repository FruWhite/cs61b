public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double g_constant = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p2){
        double dx = this.xxPos - p2.xxPos;
        double dy = this.yyPos - p2.yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double calcForceExertedBy(Planet p2){
        return g_constant * this.mass * p2.mass
                / Math.pow(calcDistance(p2), 2);
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dx / r;
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        return calcForceExertedBy(p) * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allP){
        double result = .0;
        for(Planet p : allP){
            if (!p.equals(this)){
                result += calcForceExertedBy(p) / calcDistance(p)
                    * (p.xxPos - xxPos);}
            else continue;
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allP){
        double result = .0;
        for(Planet p : allP){
            if (!p.equals(this)){
            result += calcForceExertedBy(p) / calcDistance(p)
                    * (p.yyPos - yyPos);}
            else continue;
        }
        return result;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
        return;
    }



    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}
















