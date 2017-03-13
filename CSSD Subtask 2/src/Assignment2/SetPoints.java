
package Assignment2;

/**
 * Displays a jxMap, the user then clicks to place down markers were they want to 
 * lay out their field. The coordinates of the markers are then stored in a list.
 * @return A list of GPSCoordList
 * @author Callum
 */

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class SetPoints extends MapView {
    
    ArrayList<LatLng> points;
    
    public SetPoints() {
        
        points = new ArrayList<>();
        
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Create a map and set the options for it
                    final Map map = getMap();
                    MapOptions options = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.LEFT_TOP);
                    options.setMapTypeControlOptions(controlOptions);
                    map.setOptions(options);
                    
                    // Setting the map center
                    map.setCenter(new LatLng(52.888322, -1.368584));
                    
                    // Setting initial zoom value
                    map.setZoom(6.0);
                    
                    
                    // Adding event listener that intercepts clicking on map
                    map.addEventListener("click", new MapMouseEvent() {
                        @Override
                        public void onEvent(MouseEvent mouseEvent) {
                            
                            // Creating a new marker
                            final Marker marker = new Marker(map);
                            marker.setPosition(mouseEvent.latLng());
                            
                            // Create a window that displays the long and lat of the marker after it is placed
                            final InfoWindow infoWindow = new InfoWindow(map);
                            infoWindow.setContent(mouseEvent.latLng().toString());
                            infoWindow.open(map, marker);
                            
                            // Adding event listener that intercepts clicking on marker
                            marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                    marker.remove();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void openMap() {
        final SetPoints setPoints = new SetPoints();

        JFrame frame = new JFrame("Markers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(setPoints, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        
    }
}