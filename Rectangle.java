import java.awt.image.BufferedImage;
import java.io.IOException;

import fileio.implementations.FileReader;
//clasa Rectangle implementeaza Visitable (deci are metoda accept)
public class Rectangle implements Visitable {
    private int xStanga;
    private int yStanga;
    private int inaltime;
    private int lungime;
    private int culContur;
    private int culInterior;

    public Rectangle() {
        }

    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
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

    public final int getInaltime() {
        return inaltime;
        }

    public final void setInaltime(final int inaltime) {
        this.inaltime = inaltime;
        }

    public final int getLungime() {
        return lungime;
        }

    public final void setLungime(final int lungime) {
        this.lungime = lungime;
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


    // functia de citire a tuturor dimensiunilor caracteristice dreptunghiului
    public final void citire(final FileReader f) {
        try {
            int x = f.nextInt();
            this.xStanga = x;

            int y = f.nextInt();
            this.yStanga = y;

            int inal = f.nextInt();
            this.inaltime = inal;

            int lung = f.nextInt();
            this.lungime = lung;
            //culoare conturului dreptunghiului
            Algoritmi alg = new Algoritmi();
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            this.culContur = alg.citireCul(culoareCont, aCont);

            //culoare interiorului dreptunghiului
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

