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

import components.BaseProductSearchValidator;
import components.map.HintTitleWindow;
import components.map.MarkerData;

import constants.GoogleConstants;
import constants.MapDataGridConstants;
import constants.WidgetConstants;

import events.map.InfoWindowClosedEvent;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.events.ListEvent;
import mx.events.IndexChangedEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import util.MapUtils;

import vo.ActivitySearchVO;

[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var searchItems:ArrayCollection;
[Bindable] private var activitySearchVO:ActivitySearchVO;
[Bindable] public var allMarkersDataProvider:ArrayCollection = new ArrayCollection();

private var zoomControl:ZoomControl = new ZoomControl(); 
private var positionControl:PositionControl = new PositionControl();

private function init():void {					
    validatorArr = new Array();    
        
    adultsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();    
    childrenComboBox.dataProvider = WidgetConstants.getPaxSelectableData();
    infantsComboBox.dataProvider = WidgetConstants.getPaxSelectableData(); 
    typeOfActivityComboBox.dataProvider = WidgetConstants.getActivitiesSelectableData();   
    locationComboBox.dataProvider = WidgetConstants.getLocationsSelectableData();
    
    activitySearchVO = new ActivitySearchVO(); 
    totalPaxTextBox.text = "0";  
    	  	          
}

private function validateForm(evt:MouseEvent):void {
	if (new BaseProductSearchValidator(validatorArr).validateForm(evt)) {
	    search();
	}	
}



private function resetForm(evt:MouseEvent):void {	
	locationComboBox.selectedIndex=-1; 	
	departingDate.selectedDate = null;
	returningDate.selectedDate = null;
	typeOfActivityComboBox.selectedIndex=-1; 
	 
    keywordSearchTextBox.text = ""; 
    totalPaxTextBox.text = "0";    
}

private function updateTotalPax(evt:ListEvent):void {	
	var totalPax:int = 0;	
	if (adultsComboBox.selectedLabel != null) {
		totalPax+= int(adultsComboBox.selectedLabel);
	} 
		
	if (childrenComboBox.selectedLabel != null) {
		totalPax+= int(childrenComboBox.selectedLabel);
	}
	
	if (infantsComboBox.selectedLabel != null) {
		totalPax+= int(infantsComboBox.selectedLabel);
	}
	
	totalPaxTextBox.text = totalPax.toString();		
}


public function search():void {
    this.activitySearchVO = new ActivitySearchVO();
   // this.accommodationSearchVO.id = new Number(idText.text);
    //this.accommodationSearchVO.name = nameText.text;
    //accommodationSearchController.search(accommodationSearchVO);
}

public function onResultDoSearch(event:ResultEvent):void {
    searchItems = event.result as ArrayCollection;
}

public function onFault(event:FaultEvent):void {
    Alert.show(event.fault.message);
}



// MAP BASED CODE - HERE (for Activity Locator)
private function tabChanged(evt:IndexChangedEvent):void {	
	if (activitySearchTabNavigator.selectedIndex == 1) {
		activitiesLocationsGrid.dataProvider = MapDataGridConstants.ACTIVITY_MARKERS;
	}
}


public function onMapReady(event:MapEvent):void {	
     map.setCenter(new LatLng(-41.2889, 174.7772), 5, MapType.NORMAL_MAP_TYPE);   // centre over Wellington  
     
     map.enableScrollWheelZoom();
	 map.enableContinuousZoom();
	 map.addEventListener(MapZoomEvent.ZOOM_CHANGED, onMapZoom);
	 map.addEventListener(MapMoveEvent.MOVE_END, onMapMoveEnd);
	 	 
	 map.addControl(zoomControl);
	 map.addControl(positionControl);
	 	 
	  
     //addMarkersToMap();  
}

public function displayActivityLocationsOnMap(event:MouseEvent):void{ 
	removeMarkersFromMap();
	addMarkersToMap(MapDataGridConstants.SKIING_MARKERS);
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
	//removeMarkersFromMap();	
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
    	
//    	if (existsInSelectedLocations(o["markerData"], marker)) {
//    		hintTitleWindow.displayClickToSelectLabel = false;
//    	}
//    	else { 
//    	 	hintTitleWindow.displayClickToSelectLabel = true;
//    	}
    	 
    	hintTitleWindow.displayClickToSelectLabel = false; 
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

public function locateActivityLocationsOnMap(event:MouseEvent):void{     
   
    var o:Object=  allMarkersDataProvider.getItemAt(activitiesLocationsGrid.selectedIndex) as Object;    
    var marker:Marker = o.marker;
    
    var hintTitleWindow:HintTitleWindow = new HintTitleWindow();
    hintTitleWindow.markerData = o["markerData"];    
    hintTitleWindow.marker = marker;  
    hintTitleWindow.displayClickToSelectLabel = false;    
    hintTitleWindow.setupHintTitleWindow();
     
    openHintInfoWindow(hintTitleWindow, marker);  
}


public function closed(event:InfoWindowClosedEvent):void{
     var marker:Marker = event.marker;
     var latLng:LatLng = marker.getLatLng();
     var markerData:MarkerData = new MarkerData(latLng.lat(),latLng.lng(),event.inputName, 0, "", "");
     var o:Object = MapUtils.getMarkerDataByLatAndLng(latLng.lat(),latLng.lng(),allMarkersDataProvider);
     
     //if (o != null && !existsInSelectedLocations(markerData,marker)) {
     //     addMarkerToGrid(markerData,marker);
     //}     
}

