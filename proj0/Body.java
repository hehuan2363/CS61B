import java.lang.Math;

public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;
    private static final double G = 6.67e-11;
    
    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx2 = (b.xxPos - this.xxPos)*(b.xxPos - this.xxPos);
        double dy2 = (b.yyPos - this.yyPos)*(b.yyPos - this.yyPos);
        double r2 = dx2 + dy2;
        return Math.sqrt(r2);
    }

    public double calcForceExertedBy(Body b){
        double force;
        double r = this.calcDistance(b);
        force = (Body.G * this.mass * b.mass)/(r*r);
        return force;
    }

    public double calcForceExertedByX(Body b){
        double force,forceX;
        double r = this.calcDistance(b);
        force = this.calcForceExertedBy(b);
        double dx =  b.xxPos - this.xxPos;
        forceX = force * dx / r ;
        return forceX;
    }

    public double calcForceExertedByY(Body b){
        double force,forceY;
        double r = this.calcDistance(b);
        force = this.calcForceExertedBy(b);
        double dy =  b.yyPos - this.yyPos;
        forceY = force * dy / r ;
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] allB){
        double netForceX = 0.0;
        for(int i=0;i < allB.length;i++){
            if(this.equals(allB[i])){
                continue;
            }else{
                netForceX = netForceX + this.calcForceExertedByX(allB[i]);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allB){
        double netForceY = 0.0;
        for(int i=0;i < allB.length;i++){
            if(this.equals(allB[i])){
                continue;
            }else{
                netForceY = netForceY + this.calcForceExertedByY(allB[i]);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX,aY;
        aX = fX / this.mass;
        aY = fY / this.mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"/images/"+imgFileName);
        StdDraw.show();
    }
}