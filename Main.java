import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import fileio.implementations.FileReader;

public abstract class Main {

    public static void main(final String[] args) {
        // instantiem drawvisitor
        DrawVisitor vis = new DrawVisitor();
        // obtinem singura instanta a ShapeFactory
        ShapeFactory fact = ShapeFactory.getInstance();
        // file-ul ce trebuie generat se numeste drawing.png
        File f = new File("drawing.png");
        FileReader fr;
        try {
            fr = new FileReader(args[0]);
            // citim nr de figuri
            int nrFiguri = fr.nextInt();

            // cream canvasul la inceput
            fr.nextWord();
            // folosind metoda de accesare a singurei instante a canvasului
            Canvas canvas = Canvas.getInstance();
            // citim si initializam campurile canvasului
            canvas.citire(fr);

            // pentru fiecare figura
            for (int i = 0; i < nrFiguri - 1; i++) {
                // citim tipul de figura
                String tipShape = fr.nextWord();
                // folosind instanta lui ShapeFactory obtinem o instanta
                // a figurii noi
                Visitable shape = fact.getShape(tipShape);
                // citim si initializam campurile figurii
                shape.citire(fr);
                // cu ajutorul metodei accept, desenam pe canvas
                // figura necesara
                shape.accept(vis, canvas.getIm());
                }

            fr.close();

            try {
                // cream fisierul de output, folosind canvasul
                ImageIO.write(canvas.getIm(), "PNG", f);
                } catch (IOException e) {
                    e.printStackTrace();
                    }

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    }
        }
    }

