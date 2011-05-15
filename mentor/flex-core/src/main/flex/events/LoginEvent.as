package events
{
	import flash.events.Event;
	
	public class LoginEvent extends Event
	{
		public var username:String;
		public var password:String;
		
		public static const LOGIN:String = "login";
		
		public function LoginEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);			
		}
		
		override public function clone():Event
		{
			var newEvent:LoginEvent = new LoginEvent(type);
			newEvent.username = username;
			newEvent.password = password;
			
			return newEvent;	
		}	

	}
	
}