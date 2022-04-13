package org.vsdl.astral.models.simple;

import java.awt.*;

public class Short2DArray {

    private final int height;
    private final int width;

    private final short[][] content;

    public Short2DArray(Dimension dimension) {
        this(dimension.height, dimension.width);
    }

    public Short2DArray(int height, int width) {
        this.height=height;
        this.width=width;
        this.content=new short[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setContent(int row, int column, short value) {
        content[row][column] = value;
    }

    public short getContent(int row, int column) {
        return content[row][column];
    }
}
