package org.vsdl.astral.models.world;

public class WorldCoordinate {

    private final int realm;
    private final int latitude;
    private final int longitude;
    private final int altitude;

    public WorldCoordinate(int realm, int latitude, int longitude, int altitude) {
        this.realm = realm;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public int getRealm() {
        return realm;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }
}
