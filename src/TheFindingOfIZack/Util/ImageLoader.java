package TheFindingOfIZack.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

public class ImageLoader {

    /**
     * Load an Icon for use in the program
     * @param resource  string for name of icon image in form "/name.ext"
     * @return  The Icon loaded
     */
    public static Icon loadIcon(String resource){
        return new ImageIcon((ImageLoader.class.getResource(resource)));
    }

    /**
     * Load an Image for use in the program
     * @param resource string for name of image in form "/name.ext"
     * @return The image loaded
     */
    public static Image loadImage(String resource){
        Image img = new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

        try {
            img =  ImageIO.read(ImageLoader.class.getResource((resource)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
