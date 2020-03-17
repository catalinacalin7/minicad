import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import fileio.implementations.FileReader;
//clasa Polygon implementeaza Visitable (deci are metoda accept)
public class Polygon implements Visitable {
    private int nrPuncte;
    // stocam punctele poligonului in ArrayList-ul listaPuncte
    private ArrayList<Point> listaPuncte;
    private int culContur;
    private int culInterior;

    public Polygon() {
        }

    //metoda ce adauga un punct in lista de puncte a poligonului
    public final void adaugaPunct(final int x, final int y) {
        Point p = new Point(x, y);
        this.listaPuncte.add(p);
        }

    // metoda ce calculeaza centrul de greutate a poligonului
    public final Point calcCenGreut() {
        int sumX = 0;
        int sumY = 0;
        for (int i = 0; i < nrPuncte; i++) {
            sumX += this.listaPuncte.get(i).getX();
            sumY += this.listaPuncte.get(i).getY();
            }
        int xGreutate = sumX / this.nrPuncte;
        int yGreutate = sumY / this.nrPuncte;
        Point g = new Point(xGreutate, yGreutate);
        return g;
        }

    // metoda accept necesara pentru VISITOR
    public final void accept(final Visitor visitor, final BufferedImage im) {
        visitor.draw(this, im);
        }

    public final int getNrPuncte() {
        return nrPuncte;
        }

    public final void setNrPuncte(final int nrPuncte) {
        this.nrPuncte = nrPuncte;
        }

    public final ArrayList<Point> getListaPuncte() {
        return listaPuncte;
        }

    public final void setListaPuncte(final ArrayList<Point> listaPuncte) {
        this.listaPuncte = listaPuncte;
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

    // functia de citire a tuturor dimensiunilor caracteristice poligonului
    public final void citire(final FileReader f) {
        try {
            int nrP = f.nextInt();
            this.nrPuncte = nrP;

            this.listaPuncte = new ArrayList<Point>();

            for (int i = 0; i < this.nrPuncte; i++) {
                this.adaugaPunct(f.nextInt(), f.nextInt());
                }
            // culoarea conturului poligonului
            Algoritmi alg = new Algoritmi();
            String culoareCont = f.nextWord();
            int aCont = f.nextInt();
            this.culContur = alg.citireCul(culoareCont, aCont);

            // culoarea interiorului poligonului
            String culoareInt = f.nextWord();
            int aInt = f.nextInt();
            this.culInterior = alg.citireCul(culoareInt, aInt);

            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

