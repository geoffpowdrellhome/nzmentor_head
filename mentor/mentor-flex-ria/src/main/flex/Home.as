	import flash.events.MouseEvent;
	
	import mx.collections.*;
	import mx.containers.ViewStack;
	import mx.events.MenuEvent;
	
	protected const PRODUCT_MENU:int = 3;	
	protected const ITINERARY_MENU:int = 4;
    	
	// Event handler for the MenuBar control&apos;s itemClick event.
	private function menuItemHandler(event:MenuEvent):void {
		
		if (event.label == "Home" ||
	        event.label == "Cities/Regions" ||
	        event.label == "Activities") {
	        topMenuViewStack.selectedIndex = event.menuBar.selectedIndex;	
	     }
	     else if (event.label == "Cars") {
	     	topMenuViewStack.selectedIndex = 3;
	     }
		 else if (event.label == "Motor Homes") {
	     	topMenuViewStack.selectedIndex = 4;
	     }
		 else if (event.label == "Ferries") {
	     	topMenuViewStack.selectedIndex = 5;
	     }
		 else if (event.label == "Rail Tours") {
	     	topMenuViewStack.selectedIndex = 6;
	     }
	     else if (event.label == "Transfers") {
	     	topMenuViewStack.selectedIndex = 7;
	     }
	     else if (event.label == "Accommodation") {
	     	topMenuViewStack.selectedIndex = 8;
	     }
	     else if (event.label == "Suggestions") {
	     	topMenuViewStack.selectedIndex = 9;
	     }
	     else if (event.label == "Build your own") {
	     	topMenuViewStack.selectedIndex = 10;
	     }
	     else if (event.label == "View Itinerary") {
	     	topMenuViewStack.selectedIndex = 11;
	     }		
	}  
	
	private function menuBarTopLevelButtonClick(evt:MouseEvent):void {  
       // MenuBars do not send events when top level buttons are clicked.  
       // Here I interecept a click on the control itself, determine if the click was over a   
       // top level button (evt.target has 'data' property).   
       // If so repackage as an ITEM_CLICK event and dispatch so that the ITEM_CLICK  
       // handler receives the button click as one might expect and acts on it.  
       // This is a generic handler that will work for any top level menuBar button click   
       // as long as it is registered for a itemClick as well. If not, no harm, the dispatch never gets delivered.       
        
       if (evt.target.hasOwnProperty("data")) {       	    
       	 
	       // Do not dispatch event if this is not a top level button. Check to see if the XML data has children.	        
	       if (XML(evt.target.data).children().length() == 0) { 
	       	  
	       	  //Alert.show("(evt.target.data).@label = "+(evt.target.data).@label);
	          evt.target.dispatchEvent(new MenuEvent(MenuEvent.ITEM_CLICK, true, true, homePageMenuBar, null, evt.target.data, null, XML(evt.target.data).@label));	         
	       }
       }       
   } 
        
    public function initApp():void {
    	homePageMenuBar.addEventListener(MouseEvent.CLICK, menuBarTopLevelButtonClick);    	
    }
