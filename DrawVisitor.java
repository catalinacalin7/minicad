import java.awt.Point;
import java.awt.image.BufferedImage;
// Pentru operatia de desenare ce trebuie aplicata, se implementeaza clasa de tip Visitor.
// In DrawVisitor de tip Visitor trebuie sa implementam metode care aplica operatia pentru
// fiecare tip de element vizitabil (care implementeaza interfata Visitable)
public class DrawVisitor implements Visitor {

    // desenarea cercului
    public final void draw(final Circle c, final BufferedImage im) {
        Algoritmi a = new Algoritmi();
        // folosim algoritmul bresenham de desenare a cercului
        a.circleBresenham(c.getxCentru(), c.getyCentru(), c.getRaza(),
        im, c.getCulContur());

        // calculam centrul de greutate a cercului
        Point centruG = c.calcCenGreut();
        // si coloram interiorul cercului folosind algoritmul floodFill
        a.floodFill(im, (int) centruG.getX(), (int) centruG.getY(),
        c.getCulInterior(), c.getCulContur());
        }

    // desenarea rombului
    public final void draw(final Diamond d, final BufferedImage im) {
        Algoritmi a = new Algoritmi();
        // calculam semidiagonalele cu rotunjire prin lipsa a impartirii
        int semidiagVert = 0;
        int semidiagOriz = 0;
        if (Math.round(d.getDiagVert() / 2) < (d.getDiagVert() / 2)) {
            semidiagVert = Math.round(d.getDiagVert() / 2) - 1;
            } else if (Math.round(d.getDiagVert() / 2) <= (d.getDiagVert() / 2)) {
                semidiagVert = Math.round(d.getDiagVert() / 2);
                }
        if (Math.round(d.getDiagOriz() / 2) > (d.getDiagOriz() / 2)) {
            semidiagOriz = Math.round(d.getDiagOriz() / 2) - 1;
            } else if (Math.round(d.getDiagOriz() / 2) <= (d.getDiagOriz() / 2)) {
                semidiagOriz = Math.round(d.getDiagOriz() / 2);
                }

        // linia de la punctul de sus pana in punctul din partea dreapta
        Line l1 = new Line(d.getxCentru(), d.getyCentru() - semidiagVert,
        d.getxCentru() + semidiagOriz, d.getyCentru());

        a.linieBresenham(l1.getxStart(), l1.getyStart(), l1.getxFinal(),
        l1.getyFinal(), im, d.getCulContur());

        // linia de la punctul din partea dreapta pana in punctul de jos
        Line l2 = new Line(d.getxCentru() + semidiagOriz, d.getyCentru(),
        d.getxCentru(), d.getyCentru() + semidiagVert);

        a.linieBresenham(l2.getxStart(), l2.getyStart(), l2.getxFinal(),
        l2.getyFinal(), im, d.getCulContur());

        // linia de la punctul de jos pana in punctul din partea stanga
        Line l3 = new Line(d.getxCentru(), d.getyCentru() + semidiagVert,
        d.getxCentru() - semidiagOriz, d.getyCentru());

        a.linieBresenham(l3.getxStart(), l3.getyStart(), l3.getxFinal(),
        l3.getyFinal(), im, d.getCulContur());

        // linia de la punctul din partea stanga pana in punctul de sus
        Line l4 = new Line(d.getxCentru(), d.getyCentru() - semidiagVert,
        d.getxCentru() - semidiagOriz, d.getyCentru());
        a.linieBresenham(l4.getxStart(), l4.getyStart(), l4.getxFinal(),
        l4.getyFinal(), im, d.getCulContur());

        // calculam centrul de greutate a rombului
        Point centruG = d.calcCenGreut();

        // si coloram interiorul cercului folosind algoritmul floodFill
        a.floodFill(im, (int) centruG.getX(), (int) centruG.getY(),
        d.getCulInterior(), d.getCulContur());
        }

    // desenarea poligonului
    public final void draw(final Polygon p, final BufferedImage im) {
        Algoritmi a = new Algoritmi();
        // desenam liniile ce formeaza conturul poligonului, luand cate 2 puncte
        // din lista de puncte
        for (int i = 0; i < p.getNrPuncte() - 1; i++) {
            Line l1 = new Line((int) p.getListaPuncte().get(i).getX(),
            (int) p.getListaPuncte().get(i).getY(),
            (int) p.getListaPuncte().get(i + 1).getX(),
            (int) p.getListaPuncte().get(i + 1).getY());

            a.linieBresenham(l1.getxStart(), l1.getyStart(), l1.getxFinal(),
            l1.getyFinal(), im, p.getCulContur());
            }
        // ultima linie
        Line l1 = new Line((int) p.getListaPuncte().get(p.getNrPuncte() - 1).getX(),
                (int) p.getListaPuncte().get(p.getNrPuncte() - 1).getY(),
                (int) p.getListaPuncte().get(0).getX(),
                (int) p.getListaPuncte().get(0).getY());
        a.linieBresenham(l1.getxStart(), l1.getyStart(), l1.getxFinal(),
        l1.getyFinal(), im, p.getCulContur());

        // calculam centrul de greutate a poligonului
        Point centruG = p.calcCenGreut();
        // si coloram interiorul poligonului folosind algoritmul floodFill
        a.floodFill(im, (int) centruG.getX(), (int) centruG.getY(),
        p.getCulInterior(), p.getCulContur());
        }

    // desenarea liniei
    public final void draw(final Line l, final BufferedImage im) {
        Algoritmi a = new Algoritmi();
        // folosim algoritmul bresenham de desenare a liniei
        a.linieBresenham(l.getxStart(), l.getyStart(), l.getxFinal(),
        l.getyFinal(), im, l.getArgb());
        }

    // desenarea dreptunghiului
    public final void draw(final Rectangle r, final BufferedImage im) {
        Algoritmi a = new Algoritmi();

        //linia de sus orizontala
        Line l1 = new Line(r.getxStanga(), r.getyStanga(),
        r.getxStanga() + r.getLungime() - 1, r.getyStanga());

        a.linieBresenham(l1.getxStart(), l1.getyStart(), l1.getxFinal(),
        l1.getyFinal(), im, r.getCulContur());

        //linia de jos orizontala
        Line l2 = new Line(r.getxStanga(), r.getyStanga() + r.getInaltime() - 1,
        r.getxStanga() + r.getLungime() - 1, r.getyStanga()
        + r.getInaltime() - 1);

        a.linieBresenham(l2.getxStart(), l2.getyStart(), l2.getxFinal(),
        l2.getyFinal(), im, r.getCulContur());

        //linia din stanga verticala
        Line l3 = new Line(r.getxStanga(), r.getyStanga(), r.getxStanga(),
        r.getyStanga() + r.getInaltime() - 1);

        a.linieBresenham(l3.getxStart(), l3.getyStart(), l3.getxFinal(),
        l3.getyFinal(), im, r.getCulContur());

        //linia din dreapta verticala
        Line l4 = new Line(r.getxStanga() + r.getLungime() - 1, r.getyStanga(),
        r.getxStanga() + r.getLungime()  - 1,
        r.getyStanga() + r.getInaltime() - 1);

        a.linieBresenham(l4.getxStart(), l4.getyStart(), l4.getxFinal(),
        l4.getyFinal(), im, r.getCulContur());

        // coloram interiorul dreptunghiului
        for (int i = r.getxStanga() + 1; i < r.getxStanga() + r.getLungime() - 1; i++) {
            for (int j = r.getyStanga() + 1; j < r.getyStanga() + r.getInaltime() - 1;
                j++) {
                // daca apartine canvasului - coloram
                if (a.apartineCanvas(i, j, im)) {
                    im.setRGB(i, j, r.getCulInterior());
                    }
                }
            }
        }

    // desenarea patratului
    public final void draw(final Square s, final BufferedImage im) {
        Algoritmi a = new Algoritmi();

        //linia de sus orizontala
        Line l1 = new Line(s.getxStanga(), s.getyStanga(),
        s.getxStanga() + s.getLungimeL() - 1, s.getyStanga());

        a.linieBresenham(l1.getxStart(), l1.getyStart(),
        l1.getxFinal(), l1.getyFinal(), im, s.getCulContur());

        //linia de jos orizontala
        Line l2 = new Line(s.getxStanga(), s.getyStanga() + s.getLungimeL() - 1,
        s.getxStanga() + s.getLungimeL() - 1,
        s.getyStanga() + s.getLungimeL() - 1);

        a.linieBresenham(l2.getxStart(), l2.getyStart(), l2.getxFinal(),
        l2.getyFinal(), im, s.getCulContur());

        //linia din stanga verticala
        Line l3 = new Line(s.getxStanga(), s.getyStanga(), s.getxStanga(),
        s.getyStanga() + s.getLungimeL() - 1);

        a.linieBresenham(l3.getxStart(), l3.getyStart(), l3.getxFinal(),
        l3.getyFinal(), im, s.getCulContur());

        //linia din dreapta verticala
        Line l4 = new Line(s.getxStanga() + s.getLungimeL() - 1, s.getyStanga(),
        s.getxStanga() + s.getLungimeL()  - 1,
        s.getyStanga() + s.getLungimeL() - 1);

        a.linieBresenham(l4.getxStart(), l4.getyStart(), l4.getxFinal(),
        l4.getyFinal(), im, s.getCulContur());

        // coloram interiorul patratului
        for (int i = s.getxStanga() + 1; i < s.getxStanga() + s.getLungimeL() - 1; i++) {
            for (int j = s.getyStanga() + 1; j < s.getyStanga() + s.getLungimeL() - 1;
                 j++) {
                // daca apartine canvasului
                if (a.apartineCanvas(i, j, im)) {
                    im.setRGB(i, j, s.getCulInterior());
                    }
                }
            }
        }

    // desenarea triunghiului
    public final void draw(final Triangle t, final BufferedImage im) {
        Algoritmi a = new Algoritmi();
        // linia ce uneste primul punct cu al doilea
        Line l1 = new Line(t.getxPrim(), t.getyPrim(), t.getxDoi(), t.getyDoi());
        a.linieBresenham(l1.getxStart(), l1.getyStart(), l1.getxFinal(),
        l1.getyFinal(), im, t.getCulContur());

        // linia ce uneste al doilea punct cu al treilea
        Line l2 = new Line(t.getxDoi(), t.getyDoi(), t.getxTrei(), t.getyTrei());
        a.linieBresenham(l2.getxStart(), l2.getyStart(), l2.getxFinal(),
        l2.getyFinal(), im, t.getCulContur());

        // linia ce uneste al treilea punct cu primul
        Line l3 = new Line(t.getxTrei(), t.getyTrei(), t.getxPrim(), t.getyPrim());
        a.linieBresenham(l3.getxStart(), l3.getyStart(), l3.getxFinal(),
        l3.getyFinal(), im, t.getCulContur());

        // calculam centrul de greutate a triunghiului
        Point centruG = t.calcCenGreut();
        // si coloram interiorul triunghiului folosind algoritmul floodFill
        a.floodFill(im, (int) centruG.getX(), (int) centruG.getY(),
        t.getCulInterior(), t.getCulContur());
        }
    }
