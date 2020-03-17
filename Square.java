import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
//clasa Square implementeaza Visitable (deci are metoda accept)
public class Square implements Visitable {
    private int xStanga;
    private int yStanga;
    private int lungimeL;
    private int culContur;
    private int culInterior;

    public Square() {
        }

    public final int getxStanga() {
        return xStanga;
        }

    public final void setxStanga(final int xStanga) {
        this.xStanga = xStanga;
        }

    public final int getyStanga() {
        return yStanga;
        }

    public final void setyStanga(final int yStanga) {
        this.yStanga = yStanga;
        }

    public final int getLungimeL() {
        return lungimeL;
        }

    public final void setLungimeL(final int lungimeL) {
        this.lungimeL = lungimeL;
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
    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }


    // functia de citire a tuturor dimensiunilor caracteristice patratului
    public final void citire(final FileReader f) {
        try {
            int x = f.nextInt();
            this.xStanga = x;

            int y = f.nextInt();
            this.yStanga = y;

            int lungime = f.nextInt();
            this.lungimeL = lungime;
            // culoarea conturului figurii
            Algoritmi alg = new Algoritmi();
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            this.culContur = alg.citireCul(culoareCont, aCont);
            // culoarea interiorului figurii
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

