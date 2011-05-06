package events
{	
	import flash.events.Event;
	
	public class SelectProductSearchScreenEvent extends Event
	{
		public var selectedIndex:int;
		
		public static const SELECT_PRODUCT_SEARCH_SCREEN:String = "selectProductSearchScreen";
		
		public function SelectProductSearchScreenEvent(type:String, bubbles:Boolean=true, cancelable:Boolean=false)
		{			
			super(type, bubbles, cancelable);		
		}
			
		override public function clone():Event
		{
			var newEvent:SelectProductSearchScreenEvent = new SelectProductSearchScreenEvent(type);
			newEvent.selectedIndex = selectedIndex;
						
			return newEvent;	
		}	
		

	}
}