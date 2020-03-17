import java.awt.image.BufferedImage;
import java.io.IOException;
import fileio.implementations.FileReader;;

public final class Canvas {
    private int inaltime;
    private int latime;
    private BufferedImage im;
    private int argb;

    // aplicam Singleton
    // singura instanta necesara
    private static Canvas instance = new Canvas();

    // pentru Singleton, constructorul este private
    private Canvas() {
        }

    // metoda ce returneaza singura instanta
    public static Canvas getInstance() {
        return instance;
        }

    public int getInaltime() {
        return inaltime;
        }


    public void setInaltime(final int inaltime) {
        this.inaltime = inaltime;
        }


    public int getLatime() {
        return latime;
        }


    public void setLatime(final int latime) {
        this.latime = latime;
        }


    public BufferedImage getIm() {
        return im;
        }


    public void setIm(final BufferedImage im) {
        this.im = im;
        }


    public int getArgb() {
        return argb;
        }


    public void setArgb(final int argb) {
        this.argb = argb;
        }

    // functia de citire a tuturor dimensiunilor caracteristice canvasului
    public void citire(final FileReader f) {
        try {
            int lat = f.nextInt();
            this.latime = lat;

            int inalt = f.nextInt();
            this.inaltime = inalt;
            // tot aici, cream canvasul
            this.im = new BufferedImage(inaltime, latime, BufferedImage.TYPE_INT_ARGB);

            String culoare = f.nextWord();
            int a = f.nextInt();
            Algoritmi alg = new Algoritmi();
            this.argb = alg.citireCul(culoare, a);
            // coloram canvasul cu culoarea necesara
            for (int i = 0; i < inaltime; i++) {
                for (int j = 0; j < latime; j++) {
                    this.im.setRGB(i, j, argb);
                    }
                }
            } catch (IOException e) { // inchidem try
                e.printStackTrace();
                }
        }
    }

