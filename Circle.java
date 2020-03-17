import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
// clasa Circle implementeaza Visitable (deci are metoda accept)
public class Circle implements Visitable {

    private int xCentru;
    private int yCentru;
    private int raza;
    private int culContur;
    private int culInterior;

    public Circle() {
        }

    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }

    // metoda ce calculeaza centrul de greutate a cercului
    public final Point calcCenGreut() {
        int xGreutate = xCentru;
        int yGreutate = yCentru;
        Point g = new Point(xGreutate, yGreutate);
        return g;
        }

    public final int getxCentru() {
        return xCentru;
        }

    public final void setxCentru(final int xCentru) {
        this.xCentru = xCentru;
        }

    public final int getyCentru() {
        return yCentru;
        }

    public final void setyCentru(final int yCentru) {
        this.yCentru = yCentru;
        }

    public final int getRaza() {
        return raza;
        }

    public final void setRaza(final int raza) {
        this.raza = raza;
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


    // functia de citire a tuturor dimensiunilor caracteristice cercului
    public final void citire(final FileReader f) {
        try {
            int x = f.nextInt();
            this.xCentru = x;

            int y = f.nextInt();
            this.yCentru = y;

            int raz = f.nextInt();
            this.raza = raz;
            // culoarea conturului cercului
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            Algoritmi alg = new Algoritmi();
            this.culContur = alg.citireCul(culoareCont, aCont);
            // culoarea interiorului cercului
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

