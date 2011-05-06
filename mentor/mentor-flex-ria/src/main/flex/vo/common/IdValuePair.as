package vo.common
{
	public class IdValuePair
	{				   
	    [Bindable] public var id:int;	    
	    [Bindable] public var name:String;
		
		public function IdValuePair(_id:int, _name:String)	
		{			
			this.id = _id;
			this.name = _name;		
		}
		
		public function getId():int {
			return this.id;
		}
		
		public function getName():String {
			return this.name;
		}
		

	}
}