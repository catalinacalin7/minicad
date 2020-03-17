import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Algoritmi {
    //constantele necesare
    private final int constCitire1 = 3;
    private final int constCitire2 = 5;
    private final int constCitire3 = 7;
    private final int radix = 16;
    private final int constCircle1 = 3;
    private final int constCircle2 = 4;
    private final int constCircle4 = 10;
    private final int constCircle5 = 6;

    // functie ce transforma #RGB A in culoare de tip int
    public final int citireCul(final String culoare, final int alpha) {
        //red
        int r = Integer.valueOf(culoare.substring(1, constCitire1), radix);
        //green
        int g = Integer.valueOf(culoare.substring(constCitire1, constCitire2), radix);
        //blue
        int b = Integer.valueOf(culoare.substring(constCitire2, constCitire3), radix);
        //obtinem culoarea, folosind clasa Color
        int rezCuloare = (new Color(r, g, b, alpha)).getRGB();
        return rezCuloare;
        }

    // metoda ce verifica daca un punct(x,y) apartine canvasului
    public final boolean apartineCanvas(final int x, final int y, final BufferedImage im) {
        return x >= 0 && x < im.getWidth() && y >= 0 && y < im.getHeight();
        }

    // algoritmul Bresenham de desenare a liniei
    public final void linieBresenham(final int x1, final int y1, final int x2,
        final int y2, final BufferedImage im, final int rgb) {
        // Initializarea variabilelor
        int x = x1;
        int y = y1;
        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int s1 = (int) Math.signum(x2 - x1);
        int s2 = (int) Math.signum(y2 - y1);
        boolean interchange = false;

        //Interchange dintre deltaX si deltaY
        if (deltaY > deltaX) {
            int temp = deltaX;
            deltaX = deltaY;
            deltaY = temp;
            interchange = true;
            }

        int error = 2 * deltaY - deltaX;

        for (int i = 0; i <= deltaX; i++) {
            if (this.apartineCanvas(x, y, im)) {
                im.setRGB(x, y, rgb);
                }

            while (error > 0) {
                if (interchange) {
                    x = x + s1;
                    } else {
                        y = y + s2;
                        }
                error = error - 2 * deltaX;
                }

            if (interchange) {
                y = y + s2;
                } else {
                    x = x + s1;
                    }
            error = error + 2 * deltaY;
            }
        }
    // functie ce deseneaza cerc, bazata pe algoritmul Bresenham de
    // desenare a cercurilor
    public final void drawCircle(final int xc, final int yc, final int x,
        final int y, final BufferedImage im, final int argb) {
        //desenam, doar daca apartine canvasului
        if (this.apartineCanvas(xc + x, yc + y, im)) {
            im.setRGB(xc + x, yc + y, argb);
            }
        if (this.apartineCanvas(xc - x, yc + y, im)) {
            im.setRGB(xc - x, yc + y, argb);
            }
        if (this.apartineCanvas(xc + x, yc - y, im)) {
            im.setRGB(xc + x, yc - y, argb);
            }
        if (this.apartineCanvas(xc - x, yc - y, im)) {
            im.setRGB(xc - x, yc - y, argb);
            }
        if (this.apartineCanvas(xc + y, yc + x, im)) {
            im.setRGB(xc + y, yc + x, argb);
            }
        if (this.apartineCanvas(xc - y, yc + x, im)) {
            im.setRGB(xc - y, yc + x, argb);
            }
        if (this.apartineCanvas(xc + y, yc - x, im)) {
            im.setRGB(xc + y, yc - x, argb);
            }
        if (this.apartineCanvas(xc - y, yc - x, im)) {
            im.setRGB(xc - y, yc - x, argb);
            }
        }

    // algoritmul bresenham propriu-zis, ce foloseste functia de mai sus
    public final void circleBresenham(final int xc, final int yc, final int r,
        final BufferedImage im, final int argb) {
        int x = 0;
        int y = r;
        int d = constCircle1 - 2 * r;
        while (x < y) {
            this.drawCircle(xc, yc, x, y, im, argb);
            x++;
            if (d > 0) {
                y--;
                d = d + constCircle2 * (x - y) + constCircle4;
                } else {
                    d = d + constCircle2 * x + constCircle5;
                    }
            this.drawCircle(xc, yc, x, y, im, argb);
            }
        }
    // algoritmul floodFill, ce foloseste un map si o coada(linkedList)
    public final void floodFill(final BufferedImage im, final int x, final int y,
        final int culInterior, final int culBordura) {
        // stocheaza punctele parcurse
        Map<Point, Boolean> pParcurse = new HashMap<Point, Boolean>();
        // stocheaza punctele ce trebuie parcurse
        LinkedList<Point> coadaPuncte = new LinkedList<Point>();
        // adaugam centru de greutate in coada
        coadaPuncte.add(new Point(x, y));
        //algoritmul propriu zis
        while (!coadaPuncte.isEmpty()) {
            // eliminam cate un punct din coada
            Point removedP = coadaPuncte.remove();

            if (floodVerif(im, pParcurse, removedP.x, removedP.y,
                culBordura, culInterior)) {
                // 4 directii
                coadaPuncte.add(new Point(removedP.x, removedP.y - 1));
                coadaPuncte.add(new Point(removedP.x, removedP.y + 1));
                coadaPuncte.add(new Point(removedP.x - 1, removedP.y));
                coadaPuncte.add(new Point(removedP.x + 1, removedP.y));
                }
            }
        }

    // functie necesara pentru oprirea algoritmului
    final boolean floodVerif(final BufferedImage im, final Map<Point, Boolean> pParcurse,
        final int x, final int y, final int culBordura, final int culInterior) {
        // daca x nu este in canvas
        if (x < 0) {
            return false;
            }
        // daca y nu este in canvas
        if (y < 0) {
            return false;
            }
        // daca x nu este in canvas
        if (x > im.getWidth() - 1) {
            return false;
            }
        // daca y nu este in canvas
        if (y > im.getHeight() - 1) {
            return false;
            }
        // daca punctul(x,y) a fost parcurs
        if (pParcurse.containsKey(new Point(x, y))) {
            return false;
            }
        // daca pixelul (x,y) are culoarea bordurii, nu mai coloram
        if (im.getRGB(x, y) == culBordura) {
            return false;
            }
        // altfel, coloram cu culoarea interiorului figurii
        im.setRGB(x, y, culInterior);
        // si adaugam punctul in map de puncte parcurse
        pParcurse.put(new Point(x, y), true);
        return true;
        }
}

