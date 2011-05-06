package components
{
	import mx.controls.Tree;     
	import mx.controls.listClasses.IListItemRenderer; 
	
	public class CustomTree extends Tree { 
		protected override function drawItem(item:IListItemRenderer,
		                      selected:Boolean = false,
		                      highlighted:Boolean = false,
		                      caret:Boolean = false,
		                      transition:Boolean = false):void  {             
		     super.drawItem(item, false, false, false, transition);           
		}
	} 
}