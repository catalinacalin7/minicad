import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
//clasa Line implementeaza Visitable (deci are metoda accept)
public class Line implements Visitable {
    private int xStart;
    private int yStart;
    private int xFinal;
    private int yFinal;
    private int argb;

    public Line() {
        }

    public Line(final int xStart, final int yStart, final int xFinal, final int yFinal) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        }

    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }

    public final int getxStart() {
        return xStart;
        }

    public final void setxStart(final int xStart) {
        this.xStart = xStart;
        }

    public final int getyStart() {
        return yStart;
        }

    public final void setyStart(final int yStart) {
        this.yStart = yStart;
        }

    public final int getxFinal() {
        return xFinal;
        }

    public final void setxFinal(final int xFinal) {
        this.xFinal = xFinal;
        }

    public final int getyFinal() {
        return yFinal;
        }

    public final void setyFinal(final int yFinal) {
        this.yFinal = yFinal;
        }

    public final int getArgb() {
        return argb;
        }

    public final void setArgb(final int argb) {
        this.argb = argb;
        }

    // functia de citire a tuturor dimensiunilor caracteristice liniei
    public final void citire(final FileReader f) {
        try {
            int x1 = f.nextInt();
            this.xStart = x1;

            int y1 = f.nextInt();
            this.yStart = y1;

            int x2 = f.nextInt();
            this.xFinal = x2;

            int y2 = f.nextInt();
            this.yFinal = y2;
            //culoarea liniei
            Algoritmi alg = new Algoritmi();
            String culoare = f.nextWord();
            int a = f.nextInt();
            this.argb = alg.citireCul(culoare, a);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

