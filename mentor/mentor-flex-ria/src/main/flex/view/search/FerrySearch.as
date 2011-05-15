import components.BaseProductSearchValidator;

import constants.WidgetConstants;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import spark.events.IndexChangeEvent;

import vo.MotorhomeSearchVO;

[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var searchItems:ArrayCollection;
[Bindable] private var motorhomeSearchVO:MotorhomeSearchVO;

private function init():void {					
    validatorArr = new Array();    
    validatorArr.push(pickupLocationValidator); 
    validatorArr.push(departingDateValidator);
    
    motorhomeSearchVO = new  MotorhomeSearchVO(); 
    
    adultsComboBox.dataProvider = WidgetConstants.getPaxSelectableStaticDataSpark();
    childrenComboBox.dataProvider = WidgetConstants.getPaxSelectableStaticDataSpark();
    infantsComboBox.dataProvider = WidgetConstants.getPaxSelectableStaticDataSpark();
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
    Alert.show("about to perform a search");
    //this.motorhomeSearchVO = new MotorhomeSearchVO();
   // this.accommodationSearchVO.id = new Number(idText.text);
    //this.accommodationSearchVO.name = nameText.text;
    //accommodationSearchController.search(accommodationSearchVO);
}

//private function updateTotalPax(evt:ListEvent):void {
private function updateTotalPax(evt:IndexChangeEvent):void {
	var totalPax:int = 0;
    if (adultsComboBox.selectedItem != null) {
		totalPax+= int(adultsComboBox.selectedItem.id);
	}
		
	if (childrenComboBox.selectedItem != null) {
        totalPax+= int(childrenComboBox.selectedItem.id);
	}
	
	if (infantsComboBox.selectedItem != null) {
        totalPax+= int(infantsComboBox.selectedItem.id);
	}
	
	totalPaxTextBox.text = totalPax.toString();		
}


public function sparkComboBoxIdValuePairLabelFunc(item:Object):String {
    return item.name;
}



public function onResultDoSearch(event:ResultEvent):void {
    searchItems = event.result as ArrayCollection;
}

public function onFault(event:FaultEvent):void {
    Alert.show(event.fault.message);
}