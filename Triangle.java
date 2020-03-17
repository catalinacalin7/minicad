import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
//clasa Square implementeaza Visitable (deci are metoda accept)
public class Triangle implements Visitable {

    private int xPrim;
    private int yPrim;
    private int xDoi;
    private int yDoi;
    private int xTrei;
    private int yTrei;
    private int culContur;
    private int culInterior;
    private final int nrPuncte = 3;

    public Triangle() {
        }

    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }

    // metoda ce calculeaza centrul de greutate a triunghiului
    public final Point calcCenGreut() {
        int xGreutate = Math.round((xPrim + xDoi + xTrei) / nrPuncte);
        int yGreutate = Math.round((yPrim + yDoi + yTrei) / nrPuncte);
        Point g = new Point(xGreutate, yGreutate);
        return g;
        }

    public final int getxPrim() {
        return xPrim;
        }

    public final void setxPrim(final int xPrim) {
        this.xPrim = xPrim;
        }

    public final int getyPrim() {
        return yPrim;
        }

    public final void setyPrim(final int yPrim) {
        this.yPrim = yPrim;
        }

    public final int getxDoi() {
        return xDoi;
        }

    public final void setxDoi(final int xDoi) {
        this.xDoi = xDoi;
        }

    public final int getyDoi() {
        return yDoi;
        }

    public final void setyDoi(final int yDoi) {
        this.yDoi = yDoi;
        }

    public final int getxTrei() {
        return xTrei;
        }

    public final void setxTrei(final int xTrei) {
        this.xTrei = xTrei;
        }

    public final int getyTrei() {
        return yTrei;
        }

    public final void setyTrei(final int yTrei) {
        this.yTrei = yTrei;
        }

    public final int getCulContur() {
        return culContur;
        }

    public final void setCulContur(final int culContur) {
        this.culContur = culContur;
        }

    public final int getCulInterior() {
        return culInterior;
        }

    public final void setCulInterior(final int culInterior) {
        this.culInterior = culInterior;
        }

    // functia de citire a tuturor dimensiunilor caracteristice triunghiului
    public final void citire(final FileReader f) {
        try {
            int x1 = f.nextInt();
            this.xPrim = x1;

            int y1 = f.nextInt();
            this.yPrim = y1;

            int x2 = f.nextInt();
            this.xDoi = x2;

            int y2 = f.nextInt();
            this.yDoi = y2;

            int x3 = f.nextInt();
            this.xTrei = x3;

            int y3 = f.nextInt();
            this.yTrei = y3;

            // culoarea conturului triunghiului
            Algoritmi alg = new Algoritmi();
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            this.culContur = alg.citireCul(culoareCont, aCont);

            // culoarea interiorului triunghiului
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

