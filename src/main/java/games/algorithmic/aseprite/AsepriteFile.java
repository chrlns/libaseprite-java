package games.algorithmic.aseprite;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An Aseprite file.
 * 
 * @author Christian Lins <christian@lins.me>
 */
public class AsepriteFile {
    private final DataInputStream in;
    
    private Header  header;
    private Frame[] frames;
            
    public AsepriteFile(InputStream in) {
        this.in = new DataInputStream(in);
    }
    
    public Image getImage() {
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
