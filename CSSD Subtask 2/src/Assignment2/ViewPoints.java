

package Assignment2;

/**
 * Displays a list of coordinates in a shaded polygon shape on a jxMap within a JFrame
 * @param name A string which contains the name of a field or plot
 * @param coords A GPSCoordList which holds the farms coordinates that are to be displayed
 * @author Callum
 */

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polygon;
import com.teamdev.jxmaps.PolygonOptions;
import com.teamdev.jxmaps.swing.MapView;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;



public class ViewPoints extends MapView {
    private String name;
    private GPSCoordList coords;
    
    public ViewPoints(String name, GPSCoordList coords){
        this.name = name;
        this.coords = coords;
        
        setOnMapReadyHandler(new MapReadyHandler() {
            
            @Override
            public void onMapReady(MapStatus status) {
                
                if (status == MapStatus.MAP_STATUS_OK) {
                    
                    //creation of map object and setting up of options for the map
                    final Map map = getMap();
                    MapOptions mapOptions = new MapOptions(map);
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions(map);
                    controlOptions.setPosition(ControlPosition.LEFT_TOP);
                    mapOptions.setMapTypeControlOptions(controlOptions);
                    map.setOptions(mapOptions);
                    // setting the map to center on the first coordinate for the farm
                    map.setCenter(new LatLng(map, coords.get(0).getLat(), coords.get(0).getLng()));
                    // Setting initial zoom value
                    map.setZoom(15.0);
                    
                    // Converting of GPSCoords to latlng so it can be used in the map
                    ArrayList<LatLng> pathList = new ArrayList<>();
                    
                    for(int i = 0; i<coords.size(); i++)
                    {   
                        LatLng latlng = new LatLng(map, coords.get(i).getLat(), coords.get(i).getLng());
                        pathList.add(latlng);
                    }
                    LatLng[] path = pathList.toArray(new LatLng [pathList.size()]);
                    System.out.print(path.toString());
                    
                    // Creating a new polygon object
                    Polygon polygon = new Polygon(map);
                    // Initializing the polygon with the created path
                    polygon.setPath(path);
                    
                    // changing options for the polygon
                    PolygonOptions options = new PolygonOptions(map);
                    options.setFillColor("#FF0000");
                    options.setFillOpacity(0.35);
                    options.setStrokeColor("#FF0000");
                    options.setStrokeOpacity(0.8);
                    options.setStrokeWeight(2.0);
                    polygon.setOptions(options);
                }
            }
        });
    }
    
    //this function runs a instance of the class and diplays the map in a Jframe
    public void OpenMap() {
        
        final ViewPoints viewPoints = new ViewPoints(name, coords);
        JFrame frame = new JFrame("Plot Points for: " + name);
        frame.add(viewPoints, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

}