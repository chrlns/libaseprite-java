package games.algorithmic.aseprite;

import java.io.InputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Frame {
    private FrameHeader header;
    
    public void read(InputStream in) throws IOException {
        header = new FrameHeader();
        header.read(in);
    }
}
