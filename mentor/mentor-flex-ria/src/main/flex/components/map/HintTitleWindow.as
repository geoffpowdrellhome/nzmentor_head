package components.map
{
	import com.google.maps.overlays.Marker;
	
	import events.map.InfoWindowClosedEvent;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.text.StyleSheet;
	
	import mx.containers.HBox;
	import mx.containers.Panel;
	import mx.containers.TitleWindow;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.Spacer;
	import mx.controls.TextArea;
	
	public class HintTitleWindow extends TitleWindow	
	{	
		// stylesheet variables
		public var sheet:StyleSheet;
		public var loader:URLLoader;
		
	
	   // map injected properties 			
		public var marker:Marker;
		public var markerData:MarkerData;
		public var displayClickToSelectLabel:Boolean;
		
		
		// components of this object
		private var locationImage:Image = new Image();			
		private var bestKnownForTextArea:TextArea = new TextArea();
						
		private var clickToSelectLabel:Label = new Label();	
						
		public function HintTitleWindow()
		{
			super();			
		}
		
		public function setupHintTitleWindow():void {
			this.title = markerData.name;
			
			locationImage.source = markerData.imgSourceUrl;		   		    
		    locationImage.height=140;
		    locationImage.width=110;
		   		    
		    bestKnownForTextArea.text = markerData.bestKnownForInformation;						
			bestKnownForTextArea.height=140;
			bestKnownForTextArea.width=120;			
			bestKnownForTextArea.editable=false;			
			bestKnownForTextArea.wordWrap=true;					
			bestKnownForTextArea.maxChars=150;	
												
			var topHBox:HBox = new HBox();			
			topHBox.addChildAt(locationImage, 0);
			topHBox.addChildAt(bestKnownForTextArea,1);			
			this.addChild(topHBox);				
			
			//if (displayClickToSelectLabel == true) {
				var spacer:Spacer = new Spacer();
				spacer.width=100;
				clickToSelectLabel = new Label();			
				clickToSelectLabel.text="More details";
				
				var clickToSelectHBox:HBox = new HBox();		   
			    clickToSelectHBox.addChild(spacer);
			    clickToSelectHBox.addChild(clickToSelectLabel);
				this.addChild(clickToSelectHBox);				
			//}			
									
			var req:URLRequest = new URLRequest("styles/EzyBookStyle.css");				
			loader = new URLLoader();
            loader.addEventListener(Event.COMPLETE, onCSSFileLoaded);
            loader.load(req);
		}
		
		public function closeOverLay(event:MouseEvent):void{
			var e:InfoWindowClosedEvent = new InfoWindowClosedEvent(this.title);
			marker.closeInfoWindow();
			e.marker = marker;
			marker.dispatchEvent(e);
		}
		
		public function onCSSFileLoaded(event:Event):void
        {
            sheet= new StyleSheet();
            sheet.parseCSS(loader.data);        
            
            this.styleName="ezyshowmapinfowindowheader";             
            bestKnownForTextArea.styleName="ezytextAreaForMarkerInfo";                                                                          
            clickToSelectLabel.styleName= "ezylabel"; 
        }		
		
	}
	
}