import java.awt.image.BufferedImage;
// Visitor - o interfata pentru operatia aplicata - draw
// pentru fiecare obiect care implementeaza interfata Visitable
public interface Visitor {

    void draw(Circle c, BufferedImage im);
    void draw(Diamond d, BufferedImage im);
    void draw(Polygon p, BufferedImage im);
    void draw(Line l, BufferedImage im);
    void draw(Rectangle r, BufferedImage im);
    void draw(Square s, BufferedImage im);
    void draw(Triangle t, BufferedImage im);

}

