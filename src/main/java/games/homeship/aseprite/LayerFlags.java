/* MIT License
 *
 * libaseprite-java
 * Copyright (c) 2023-2024 Christian Lins <christian@lins.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package games.homeship.aseprite;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
public enum LayerFlags {
   
    FLAG_VISIBLE(1),
    FLAG_EDITABLE(2),
    FLAG_LOCK_MOVEMENT(4),
    FLAG_BACKGROUND(8),
    FLAG_PREFER_LINKED_CELS(16),
    FLAG_DISPLAY_COLLAPSED(32),
    FLAG_REFERENCE_LAYER(64);    
    
    private final int bits;
    
    private LayerFlags(int bits) {
        this.bits = bits;
    }
    
    public int bits() {
        return bits;
    }
}
