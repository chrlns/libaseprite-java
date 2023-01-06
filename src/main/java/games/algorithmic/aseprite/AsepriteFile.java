package games.algorithmic.aseprite;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An Aseprite file.
 * 
 * @author Christian Lins <christian@lins.me>
 */
public class AsepriteFile {
    private final BufferedInputStream in;
    
    private Header  header;
    private Frame[] frames;
            
    public AsepriteFile(InputStream in) {
        if (in instanceof BufferedInputStream) {
            this.in = (BufferedInputStream)in;
        } else {
            this.in = new BufferedInputStream(in);
        }
    }
    
    public Image getImage() throws IOException {
        readASEFile();
        return null;
    }
    
    /**
     * Read the ASE file.
     */
    private void readASEFile() throws IOException {
        header = new Header();
        header.read(in);
        
        for (int i = 0; i < header.getNumFrames(); i++) {
            frames[i] = new Frame();
            frames[i].read(in);
        }
    }
}
