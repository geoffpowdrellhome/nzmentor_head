package vo.common
{
	public class SelectableVO
	{
		[Bindable]
	    public var selected : Boolean;
	    
	    [Bindable]      
	    public var id : int;
	    
	    [Bindable]
	    public var description : String;
	    				
		public function SelectableVO(_selected:Boolean, _id:int, _description:String)	
		{
			this.selected = _selected;
			this.id = _id;
			this.description = _description;
		}
		
		public function getSelected():Boolean {
			return this.selected;
		}
		
		public function getId():int {
			return this.id;
		}
		
		public function getDescription():String {
			return this.description;
		}
		

	}
}