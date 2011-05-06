package components
{	
	import mx.controls.*;
	import mx.events.ValidationResultEvent;
	import mx.validators.Validator;	
	import flash.events.MouseEvent;
	
	public class BaseProductSearchValidator
	{
		[Bindable]
        public var validatorArr:Array = new Array();
                
        public function BaseProductSearchValidator(fieldsToValidate:Array) {    
        	this.validatorArr =  fieldsToValidate;   	
        }
				
		public function validateForm(evt:MouseEvent):Boolean {
		    var validatorErrorArray:Array = Validator.validateAll(validatorArr);
		    var isValidForm:Boolean = validatorErrorArray.length == 0;
		    if (isValidForm) {		        
		        return true;
		    } else {
		        var err:ValidationResultEvent;
		        var errorMessageArray:Array = [];
		        for each (err in validatorErrorArray) {
		        	var errField:String = err.currentTarget.source.name;            
		            errorMessageArray.push(errField + ": " + err.message);
		        }
		        
		        Alert.show(errorMessageArray.join("\n\n"), "Invalid search form...", Alert.OK);
		        return false;
		    }
		}
				
	}
}