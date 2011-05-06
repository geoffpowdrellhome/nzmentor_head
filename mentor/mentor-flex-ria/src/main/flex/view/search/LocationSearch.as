import com.google.maps.InfoWindowOptions;
import com.google.maps.LatLng;
import com.google.maps.LatLngBounds;
import com.google.maps.MapEvent;
import com.google.maps.MapMouseEvent;
import com.google.maps.MapMoveEvent;
import com.google.maps.MapType;
import com.google.maps.MapZoomEvent;
import com.google.maps.controls.PositionControl;
import com.google.maps.controls.ZoomControl;
import com.google.maps.overlays.Marker;
import com.google.maps.overlays.MarkerOptions;
import com.google.maps.styles.FillStyle;

import components.map.HintTitleWindow;
import components.map.MarkerData;

import constants.GoogleConstants;
import constants.MapDataGridConstants;


import events.map.InfoWindowClosedEvent;

import flash.events.MouseEvent;
import mx.validators.Validator;
import mx.events.ValidationResultEvent;


import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.core.BitmapAsset;
import mx.rpc.events.FaultEvent;

import util.MapUtils;

import vo.LocationSearchVO;

[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var selectedLocationItems:ArrayCollection;
[Bindable] public var allMarkersDataProvider:ArrayCollection = new ArrayCollection();
[Bindable] public var selectedMarkersDataProvider:ArrayCollection = new ArrayCollection();
[Bindable] private var locationSearchVO:LocationSearchVO;

private var zoomControl:ZoomControl = new ZoomControl(); 
private var positionControl:PositionControl = new PositionControl();

public function onMapReady(event:MapEvent):void {
			
     map.setCenter(new LatLng(-41.2889, 174.7772), 5, MapType.NORMAL_MAP_TYPE);   // centre over Wellington  
     
     map.enableScrollWheelZoom();
	 map.enableContinuousZoom();
	 map.addEventListener(MapZoomEvent.ZOOM_CHANGED, onMapZoom);
	 map.addEventListener(MapMoveEvent.MOVE_END, onMapMoveEnd);
	 	 
	 map.addControl(zoomControl);
	 map.addControl(positionControl);
}

public function displayPopularOnMap(event:MouseEvent):void{ 
	removeMarkersFromMap();
    addMarkersToMap(MapDataGridConstants.POPULAR_LOCATION_MARKERS);	
}

public function displayMajorCentresOnMap(event:MouseEvent):void{ 
	removeMarkersFromMap();
	addMarkersToMap(MapDataGridConstants.MAJOR_CENTRES_MARKERS);
}

public function displayActivityLocationsOnMap(event:MouseEvent):void{ 
	removeMarkersFromMap();
	addMarkersToMap(MapDataGridConstants.ACTIVITY_MARKERS);
}


private function addMarkersToMap(markerConstants:ArrayCollection):void {
	for each (var o:Object in markerConstants){
         var md:MarkerData = o["markerData"] as MarkerData;
         var latlng:LatLng = new LatLng(md.lat,md.lng);
         var maxZoom:Number = md.maxZoom;
         
         //if (map.getZoom() >= maxZoom) {
         	var markerImg:Image = new Image();
	         markerImg.source = "assets/images/mm_20_green.png";   	         
	                 
	         var markerOptions:MarkerOptions = new MarkerOptions();        
			 markerOptions.icon = markerImg; 		
			 	 		
			 markerOptions.iconOffset = new Point(-7, -18);  // use new Point(-16, -32) with images that are 32x32
			 		 	 		 
	         var marker:Marker = new Marker(latlng);        
	         marker.setOptions(markerOptions);
	                  
	         addMarkerToMap(md,marker);
         //}         
     }       
}

private function removeMarkersFromMap():void {
	for each (var o:Object in allMarkersDataProvider){
         var md:MarkerData = o["markerData"] as MarkerData;        
         map.removeOverlay(md.marker);         
     }
		
	allMarkersDataProvider.removeAll();
	allMarkersDataProvider.refresh();    
}

public function onMapMoveEnd(event:MapMoveEvent):void{	
	var zoomLevel:Number = map.getZoom();
	var latLngBounds:LatLngBounds = map.getLatLngBounds();	
	var center:LatLng = map.getLatLngBounds().getCenter();		
	//Alert.show("map move event, zoom level = "+zoomLevel+ "center.lat="+center.lat()+", center.lng="+center.lng());	
}

public function onMapZoom(event:MapZoomEvent):void{	
	removeMarkersFromMap();	
	//addMarkersToMap();	
}

public function addMarkerToMap(markerData:MarkerData, marker:Marker):void{
    var o:Object = {markerData:markerData,marker:marker};
    marker.addEventListener(MapMouseEvent.CLICK,mapMarkerClicked);
    marker.addEventListener(InfoWindowClosedEvent.NAME,closed);
    marker.addEventListener(MapMouseEvent.ROLL_OVER, showInfoWindow);
    marker.addEventListener(MapMouseEvent.ROLL_OUT, closeInfoWindow);
       
    markerData.marker = marker;
    map.addOverlay(marker);
    allMarkersDataProvider.addItem(o);    
    allMarkersDataProvider.refresh();    
}

public function addMarkerToGrid(markerData:MarkerData, marker:Marker):void{
    var o:Object = {markerData:markerData,marker:marker};
   // marker.addEventListener(MapMouseEvent.CLICK,gridLocationItemClicked);
    marker.addEventListener(InfoWindowClosedEvent.NAME,closed);
    markerData.marker = marker;    
    selectedMarkersDataProvider.addItem(o);    
    selectedMarkersDataProvider.refresh();     
}

public function removeMarkerFromGridClick(event:MouseEvent):void{
	
//     selectedMarkersDataProvider.removeItemAt(selectedLocationsGrid.selectedIndex); 
//     if(selectedLocationsGrid.selectedIndex==0){ 
//             selectedLocationsGrid.selectable=false; 
//     } 
}

public function showInfoWindow(event:MapMouseEvent):void{    
    var marker:Marker = event.target as Marker;
    
    var hintTitleWindow:HintTitleWindow = new HintTitleWindow();    
     hintTitleWindow.marker=marker;
  
    var o:Object = MapUtils.getMarkerDataByLatAndLng(marker.getLatLng().lat(), marker.getLatLng().lng(), allMarkersDataProvider);
    if (o != null){
    	hintTitleWindow.markerData = o["markerData"] as MarkerData;
    	
    	if (existsInSelectedLocations(o["markerData"], marker)) {
    		hintTitleWindow.displayClickToSelectLabel = false;
    	}
    	else { 
    	 	hintTitleWindow.displayClickToSelectLabel = true;
    	}
    	 
    	hintTitleWindow.setupHintTitleWindow();
       	
    	openHintInfoWindow(hintTitleWindow, marker);         
    }     
}

public function closeInfoWindow(event:MapMouseEvent):void{   
	var marker:Marker = event.target as Marker; 
	marker.closeInfoWindow();	
}

public function mapMarkerClicked(event:MapMouseEvent):void{    
    var marker:Marker = event.target as Marker;
    var o:Object = MapUtils.getMarkerDataByLatAndLng(marker.getLatLng().lat(), marker.getLatLng().lng(), allMarkersDataProvider);
       
     if (o != null && !existsInSelectedLocations(o["markerData"],marker)) {
          addMarkerToGrid(o["markerData"], marker);
     }    
}

public function openHintInfoWindow(hintWindow:HintTitleWindow, marker:Marker):void {
	marker.openInfoWindow(new InfoWindowOptions({customContent:hintWindow,
												 drawDefaultFrame:true,                                                                                      
                                                 hasCloseButton: true,
                                                 hasShadow: false,                                                                                       
												 customCloseRect: new Rectangle(275, 3, 23, 23),                                                                                    
                                                 fillStyle:new FillStyle({color: 0xC3C5D2, alpha: 1.0}), 
                                                 width:300,
                                                 height:220})); 
}

public function locatePopularLocationsOnMap(event:MouseEvent):void{     
   
    if (allMarkersDataProvider.list.length == 0) {
    	Alert.show("You must click 'Display on map' for Popular Spots to view on the map");
    }
    else {
    
	    var o:Object=  allMarkersDataProvider.getItemAt(popularLocationsGrid.selectedIndex) as Object;	  
	    var marker:Marker = o.marker;
	   
	    var hintTitleWindow:HintTitleWindow = new HintTitleWindow();
	    hintTitleWindow.markerData = o["markerData"];    
	    hintTitleWindow.marker = marker;  
	    hintTitleWindow.displayClickToSelectLabel = false;    
	    hintTitleWindow.setupHintTitleWindow();
	     
	    openHintInfoWindow(hintTitleWindow, marker); 
    }
}


public function locateMajorCentresOnMap(event:MouseEvent):void{  
	
	if (allMarkersDataProvider.list.length == 0) {
    	Alert.show("You must click 'Display on map' for Major Centres to view on the map");
    }
    else {	   
	    var o:Object=  allMarkersDataProvider.getItemAt(popularLocationsGrid.selectedIndex) as Object;
	    var marker:Marker = o.marker;
	   
	    var hintTitleWindow:HintTitleWindow = new HintTitleWindow();
	    hintTitleWindow.markerData = o["markerData"];    
	    hintTitleWindow.marker = marker;  
	    hintTitleWindow.displayClickToSelectLabel = false;    
	    hintTitleWindow.setupHintTitleWindow();
	     
	    openHintInfoWindow(hintTitleWindow, marker);
    }
}


public function closed(event:InfoWindowClosedEvent):void{
     var marker:Marker = event.marker;
     var latLng:LatLng = marker.getLatLng();
     var markerData:MarkerData = new MarkerData(latLng.lat(),latLng.lng(),event.inputName, 0, "", "");
     var o:Object = MapUtils.getMarkerDataByLatAndLng(latLng.lat(),latLng.lng(),allMarkersDataProvider);
     
     if (o != null && !existsInSelectedLocations(markerData,marker)) {
          addMarkerToGrid(markerData,marker);
     }     
}

private function existsInSelectedLocations(markerData:MarkerData, marker:Marker):Boolean {
	for each (var o:Object in selectedMarkersDataProvider){
         var md:MarkerData = o["markerData"] as MarkerData;
         var latlng:LatLng = new LatLng(md.lat,md.lng);
         var marker:Marker= new Marker(latlng);
         if (markerData.name == md.name)  {
         	return true;
         }
     }
     return false;
}

private function init():void {	
    popularLocationsGrid.dataProvider = MapDataGridConstants.POPULAR_LOCATION_MARKERS;	
	majorCentresLocationsGrid.dataProvider = MapDataGridConstants.MAJOR_CENTRES_MARKERS;			 
}

private function resetForm(evt:MouseEvent):void {	
	//selectedDestinations.invalidateList();
}

public function onFault(event:FaultEvent):void
{
    Alert.show(event.fault.message);
}
