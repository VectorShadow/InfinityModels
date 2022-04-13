package org.vsdl.astral.models.world;

import org.vsdl.astral.models.simple.Short2DArray;

import static org.vsdl.astral.models.util.Constants.ZONE_SIZE;

public class WorldZone {

    public enum SimulationCategory {
        TERTIARY,
        RECENT,
        SECONDARY,
        PRIMARY,
        ADJACENT,
        CONNECTED
    }

    private final WorldCoordinate worldCoordinate;

    private final SimulationCategory defaultSimulationCategory;

    //todo - theme

    private final int dangerLevel;

    private final Short2DArray tileMap;

    private WorldZone(WorldCoordinate worldCoordinate, SimulationCategory defaultSimulationCategory, int dangerLevel, Short2DArray tileMap) {
        this.worldCoordinate = worldCoordinate;
        this.defaultSimulationCategory = defaultSimulationCategory;
        this.dangerLevel = dangerLevel;
        this.tileMap = tileMap;
    }

    public WorldCoordinate getWorldCoordinate() {
        return worldCoordinate;
    }

    public SimulationCategory getDefaultSimulationCategory() {
        return defaultSimulationCategory;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public short getTileAt(int row, int column) {
        return tileMap.getContent(row, column);
    }

    public static WorldZoneBuilder builder() {
        return new WorldZoneBuilder();
    }

    public static class WorldZoneBuilder {

        private WorldCoordinate worldCoordinate;

        private SimulationCategory defaultSimulationCategory;

        //todo - theme

        private int dangerLevel;

        private final Short2DArray tileMap;

        private WorldZoneBuilder() {
            defaultSimulationCategory = SimulationCategory.TERTIARY;
            dangerLevel = -1;
            tileMap = new Short2DArray(ZONE_SIZE, ZONE_SIZE);
            for (int r = 0; r < ZONE_SIZE; ++r) {
                for (int c = 0; c < ZONE_SIZE; ++c) {
                    doSetTileValue(r, c, (short)0);
                }
            }
        }

        public WorldZoneBuilder worldCoordinate(WorldCoordinate worldCoordinate) {
            this.worldCoordinate = worldCoordinate;
            return this;
        }

        public WorldZoneBuilder simulationCategory(SimulationCategory simulationCategory) {
            this.defaultSimulationCategory = simulationCategory;
            return this;
        }

        public WorldZoneBuilder dangerLevel(int dangerLevel) {
            this.dangerLevel = dangerLevel;
            return this;
        }

        private void doSetTileValue(int row, int column, short value) {
            tileMap.setContent(row, column, value);
        }

        public WorldZoneBuilder setTileValue(int row, int column, short value) {
            doSetTileValue(row, column, value);
            return this;
        }

        public WorldZoneBuilder setTileValues(int fromRow, int fromCol, short[][] values) {
            int valueHeight = values.length;
            int valueWidth = values[0].length;
            if (fromRow < 0 || fromCol < 0) {
                throw new IllegalArgumentException("Offset must be >= 0");
            }
            if (fromRow + valueHeight >= ZONE_SIZE || fromCol + valueWidth >= ZONE_SIZE) {
                throw new IllegalArgumentException("Input out of bounds");
            }
            for (int r = 0; r < valueHeight; ++r) {
                for (int c = 0; c < valueWidth; ++c) {
                    doSetTileValue(r + fromRow, c + fromCol, values[r][c]);
                }
            }
            return this;
        }

        public WorldZoneBuilder setTileValues(short[][] values) {
            return setTileValues(0, 0, values);
        }

        public WorldZone constructWorldZone() {
            if (worldCoordinate == null) {
                throw new IllegalStateException("worldCoordinate may not be null");
            }
            if (dangerLevel < 0) {
                throw new IllegalStateException("worldCoordinate may not be null");
            }
            return new WorldZone(worldCoordinate, defaultSimulationCategory, dangerLevel, tileMap);
        }
    }
}
