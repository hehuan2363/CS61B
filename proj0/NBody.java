public class NBody{
    public static double readRadius(String f){
        In in = new In(f);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius; 
    }

    public static Body[] readBodies(String f){
        In in = new In(f);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        Body[] allB = new Body[numberOfPlanets];
        //int i = 0;
        double xxPos,yyPos,xxVel,yyVel,mass;
        String imgFileName;
        for(int i = 0; i < numberOfPlanets; i++){
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();
            allB[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }        
        return allB;
    }

    public static void main(String[] args){
        if(args.length < 3){
            System.out.println("Please supply 3 args as a command line argument.");
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.show();
        for(int i = 0; i < allBodies.length; i++){
            allBodies[i].draw();
        }
        double tt = 0.0;//time
        StdDraw.enableDoubleBuffering();
        while(tt < T){
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];

            for(int i = 0;i < allBodies.length;i++){
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }  
            StdDraw.picture(0,0,"images/starfield.jpg");     
            for(int i = 0;i < allBodies.length;i++){
                allBodies[i].update(dt, xForces[i], yForces[i]); 
                allBodies[i].draw();
            }
            //StdDraw.show();
            StdDraw.pause(5);
            tt = tt + dt;
        }
        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                        allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   
}

    }
}