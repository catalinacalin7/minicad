import java.awt.image.BufferedImage;

import fileio.implementations.FileReader;
//Visitable - interfata pentru obiecte pe care poate fi aplicata operatia draw
public interface Visitable {
    // toate obiectele ce implementeaza Visitable, trebuie sa aiba metoda
    // accept
    void accept(Visitor visitor, BufferedImage im);
    // metoda citire, care citeste si initializeaza campurile fiecarei
    // figuri ce o suprascrie(ce implementeaza clasa Visitable)
    void citire(FileReader f);
}

