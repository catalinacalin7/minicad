import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
//clasa Diamond implementeaza Visitable (deci are metoda accept)
public class Diamond implements Visitable {
    private int xCentru;
    private int yCentru;
    private int diagOriz;
    private int diagVert;
    private int culContur;
    private int culInterior;

    public Diamond() {
        }

    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }
    // metoda ce calculeaza centrul de greutate a rombului
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

    public final int getDiagOriz() {
        return diagOriz;
        }

    public final void setDiagOriz(final int diagOriz) {
        this.diagOriz = diagOriz;
        }

    public final int getDiagVert() {
        return diagVert;
        }

    public final void setDiagVert(final int diagVert) {
        this.diagVert = diagVert;
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

    // functia de citire a tuturor dimensiunilor caracteristice rombului
    public final void citire(final FileReader f) {
        try {
            int x = f.nextInt();
            this.xCentru = x;

            int y = f.nextInt();
            this.yCentru = y;

            int diagOr = f.nextInt();
            this.diagOriz = diagOr;

            int diagV = f.nextInt();
            this.diagVert = diagV;
            // culoarea conturului rombului
            Algoritmi alg = new Algoritmi();
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            this.culContur = alg.citireCul(culoareCont, aCont);

            // culoarea interiorului rombului
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

