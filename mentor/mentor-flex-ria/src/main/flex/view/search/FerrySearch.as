import components.BaseProductSearchValidator;
import flash.events.MouseEvent;
import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.events.DropdownEvent;
import mx.events.ListEvent;
import constants.WidgetConstants;
import vo.MotorhomeSearchVO;
import components.BaseProductSearchValidator;

[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var searchItems:ArrayCollection;
[Bindable] private var motorhomeSearchVO:MotorhomeSearchVO;

private function init():void {					
    validatorArr = new Array();    
    validatorArr.push(pickupLocationValidator); 
    validatorArr.push(departingDateValidator);
    
    motorhomeSearchVO = new  MotorhomeSearchVO(); 
    
    adultsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();    
    childrenComboBox.dataProvider = WidgetConstants.getPaxSelectableData();
    infantsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();
    totalPaxTextBox.text = "0"; 
    
    locateItemTypeOnMapPanel.label = "Ferry Locator";                 
}

private function validateForm(evt:MouseEvent):void {
	if (new BaseProductSearchValidator(validatorArr).validateForm(evt)) {
	    search();
	}	
}

private function resetForm(evt:MouseEvent):void {	
//	pickupLocationComboBox.selectedIndex=-1; 	
//	dropoffLocationComboBox.selectedIndex=-1;	
//	departingDate.selectedDate = null;
//	returningDate.selectedDate = null;
//	typeOfVehicleComboBox.selectedIndex=-1; 
//	priceRangeComboBox.selectedIndex=-1; 
//    keywordTextAreaBox.text = "";
//    totalPaxTextBox.text = "0";  
//    totalDurationTextBox.text = "0";  
}

public function search():void
{
    //this.motorhomeSearchVO = new MotorhomeSearchVO();
   // this.accommodationSearchVO.id = new Number(idText.text);
    //this.accommodationSearchVO.name = nameText.text;
    //accommodationSearchController.search(accommodationSearchVO);
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

public function onResultDoSearch(event:ResultEvent):void {
    searchItems = event.result as ArrayCollection;
}

public function onFault(event:FaultEvent):void {
    Alert.show(event.fault.message);
}