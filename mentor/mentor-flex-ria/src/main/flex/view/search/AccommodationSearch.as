import components.BaseProductSearchValidator;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.events.DropdownEvent;
import mx.events.ListEvent;
import constants.WidgetConstants;
import vo.AccommodationSearchVO;

[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var locationItems:ArrayCollection;
[Bindable] private var searchItems:ArrayCollection; 
[Bindable] private var accommodationSearchVO:AccommodationSearchVO;

private function init():void {					
    validatorArr = new Array();        
    validatorArr.push(locationValidator); 
    validatorArr.push(checkingInDateValidator);
    validatorArr.push(checkingOutDateValidator); 
        
    adultsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();    
    childrenComboBox.dataProvider = WidgetConstants.getPaxSelectableData();
    infantsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();    
    starRatingComboBox.dataProvider = WidgetConstants.getAccommodationStarRatingData();
    roomTypeComboBox.dataProvider = WidgetConstants.getAccommodationRoomTypeData();
    
    accommodationSearchVO = new AccommodationSearchVO(); 
    totalPaxTextBox.text = "0";  
    totalDurationTextBox.text = "0";          
}

private function validateForm(evt:MouseEvent):void {
	if (new BaseProductSearchValidator(validatorArr).validateForm(evt)) {
	    search();
	}	
}

private function resetForm(evt:MouseEvent):void {	
	locationComboBox.selectedIndex=-1; 	
	checkingInDate.selectedDate = null;
	checkingOutDate.selectedDate = null;
	roomTypeComboBox.selectedIndex=-1; 
	starRatingComboBox.selectedIndex=-1;  
    keywordSearchTextBox.text = ""; 
    totalPaxTextBox.text = "0";  
    totalDurationTextBox.text = "0"; 
}

public function search():void
{
    accommodationSearchVO = new AccommodationSearchVO();
   // this.accommodationSearchVO.id = new Number(idText.text);
    //this.accommodationSearchVO.name = nameText.text;
    //accommodationSearchController.search(accommodationSearchVO);
}

private function updateTotalDuration(evt:DropdownEvent):void {		
	if (checkingOutDate.selectedDate != null && checkingInDate.selectedDate != null) {
		totalDurationTextBox.text = (checkingOutDate.selectedDate.date - checkingInDate.selectedDate.date).toString();
	}	
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

public function onResultDoSearch(event:ResultEvent):void
{
    searchItems = event.result as ArrayCollection;
}

public function onFault(event:FaultEvent):void
{
    Alert.show(event.fault.message);
}
