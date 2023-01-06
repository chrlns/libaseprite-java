
package games.algorithmic.aseprite;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An Aseprite file.
 * 
 * @author Christian Lins <christian@lins.me>
 */
public class AsepriteFile {
    private DataInputStream in;
    
    private Header header;
            
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
    }
    
    private void readFrameHeader() {
        
    }
}
