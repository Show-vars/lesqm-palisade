package palisade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Palisade {

    
    private static double from = 5;
    private static double to = 10;
    private static double step = 1;
    
    private static int width = 1038;
    private static int leftPadding = 59;
    private static int rightPadding = 59;
    
    public static void main(String[] args) throws Exception {
        if(args.length < 2) {
            System.out.println("usage: palisade <input> <output>");
            System.exit(0);
        }
        
        PrintStream ps = new PrintStream(new FileOutputStream(new File(args[1])));
        BufferedReader headr = new BufferedReader(new InputStreamReader(Palisade.class.getResourceAsStream("head")));
        Scanner s = new Scanner(new FileInputStream(new File(args[0])));
        
        from = s.nextDouble();
        to = s.nextDouble();
        step = s.nextDouble();
        
        width = s.nextInt();
        leftPadding = s.nextInt();
        rightPadding = s.nextInt();
        
        String ln;
        while((ln = headr.readLine()) != null) {
            ps.println(ln);
        }
        int n = (int) Math.round((to - from) / step) + 1;
        
        for(int i = 0; i < n; i++) {
            int padding = ("" + ((int) (from + i * step))).length() > 1 ? 15 : 8;
            int x = (int) Math.rint(map(from, to, leftPadding, width - rightPadding, i * step + from));
            
            
            ps.println("  <text x=\"" + (x - padding) + "\" y=\"103\"  class=\"fil1 fnt0\">"+((int) (from + i * step))+"</text>");
            ps.println("  <line class=\"fil0 str1\" x1=\"" + (x) + "\" y1=\"72\" x2=\"" + (x) + "\" y2= \"80\" />");
        }
        
        while(s.hasNextDouble()) {
            double d = s.nextDouble();
            int x = (int) Math.rint(map(from, to, leftPadding, width - rightPadding, Math.abs(d)));
            
            ps.println("  <line class=\"fil0 str0\" x1=\"" + x + "\" y1=\"0\" x2=\"" + x + "\" y2= \"64\" />");
        }
        
        ps.println(" </g>\n</svg>");
        
        ps.flush();
        ps.close();
    }
    
    
    private static double map(double a1, double a2, double b1, double b2, double x) {
        return (x - a1) * (b2 - b1) / (a2 - a1) + b1;
    }
}
