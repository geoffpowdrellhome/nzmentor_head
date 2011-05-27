import components.BaseProductSearchValidator;

import constants.WidgetConstants;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.events.DropdownEvent;
import mx.events.ListEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import vo.SuggestedItinerarySearchVO;
import vo.SelectableVO;


[Bindable] public var validatorArr:Array = new Array();
[Bindable] private var countryItems:ArrayCollection;

[Bindable]
[Bindable] private var locationItems:ArrayCollection;
[Bindable] private var searchItems:ArrayCollection;

[Bindable] private var activitiesDataGridProvider:ArrayCollection;
[Bindable] private var locationsDataGridProvider:ArrayCollection;

[Bindable] private var choiceOfLocationItems:ArrayCollection;
[Bindable] private var choiceOfItineraryItems:ArrayCollection;

[Bindable] private var suggestedItinerarySearchVO:SuggestedItinerarySearchVO;

 
[Bindable] private var selectActivityCheckBox1:CheckBox = new CheckBox();
[Bindable] private var selectActivityCheckBox2:CheckBox = new CheckBox(); 
[Bindable] private var selectActivityCheckBox3:CheckBox = new CheckBox();
[Bindable] private var selectActivityCheckBox4:CheckBox = new CheckBox();
[Bindable] private var selectActivityCheckBox5:CheckBox = new CheckBox();

[Bindable] private var selectLocationCheckBox1:CheckBox = new CheckBox();
[Bindable] private var selectLocationCheckBox2:CheckBox = new CheckBox(); 
[Bindable] private var selectLocationCheckBox3:CheckBox = new CheckBox();
[Bindable] private var selectLocationCheckBox4:CheckBox = new CheckBox();
[Bindable] private var selectLocationCheckBox5:CheckBox = new CheckBox();
[Bindable] private var selectLocationCheckBox6:CheckBox = new CheckBox();
 
//[Bindable] private var selectableActivitiesVO1:SelectableVO;  
//[Bindable] private var selectableActivitiesVO2:SelectableVO; 
//[Bindable] private var selectableActivitiesVO3:SelectableVO; 
//[Bindable] private var selectableActivitiesVO4:SelectableVO;
//[Bindable] private var selectableActivitiesVO5:SelectableVO;
//
//[Bindable] private var selectableLocationsVO1:SelectableVO;
//[Bindable] private var selectableLocationsVO2:SelectableVO;
//[Bindable] private var selectableLocationsVO3:SelectableVO;
//[Bindable] private var selectableLocationsVO4:SelectableVO;
//[Bindable] private var selectableLocationsVO5:SelectableVO;
//[Bindable] private var selectableLocationsVO6:SelectableVO;

private function init():void {					
    validatorArr = new Array();    
    validatorArr.push(choiceOfIslandsValidator); 
    validatorArr.push(startDateValidator);
    validatorArr.push(endDateValidator); 
    
    suggestedItinerarySearchVO = new SuggestedItinerarySearchVO();        
     
    choiceOfIslandsComboBox.dataProvider = WidgetConstants.getChoiceOfIslandsData();            
    adultsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();    
    childrenComboBox.dataProvider = WidgetConstants.getPaxSelectableData();
    infantsComboBox.dataProvider = WidgetConstants.getPaxSelectableData();        
    
    selectableActivitiesVO1 = new SelectableVO(true, 1, "Fishing");
    selectableActivitiesVO2 = new SelectableVO(true, 2, "Skiing");
    selectableActivitiesVO3 = new SelectableVO(true, 3, "Sky diving");
    selectableActivitiesVO4 = new SelectableVO(true, 4, "Walks");
    selectableActivitiesVO5 = new SelectableVO(true, 5, "Wine tours");    
        
    activitiesDataGridProvider = new ArrayCollection();
    activitiesDataGridProvider.addItem({colSelectable:selectActivityCheckBox1, colId:selectableActivitiesVO1.id, colDescription: selectableActivitiesVO1.description});
    activitiesDataGridProvider.addItem({colSelectable:selectActivityCheckBox2, colId:selectableActivitiesVO2.id, colDescription: selectableActivitiesVO2.description});
    activitiesDataGridProvider.addItem({colSelectable:selectActivityCheckBox3, colId:selectableActivitiesVO3.id, colDescription: selectableActivitiesVO3.description});
    activitiesDataGridProvider.addItem({colSelectable:selectActivityCheckBox4, colId:selectableActivitiesVO4.id, colDescription: selectableActivitiesVO4.description});
    activitiesDataGridProvider.addItem({colSelectable:selectActivityCheckBox5, colId:selectableActivitiesVO5.id, colDescription: selectableActivitiesVO5.description});
    activitiesToIncludeGrid.dataProvider = activitiesDataGridProvider;
    
    selectableLocationsVO1 = new SelectableVO(true, 1, "Auckland");
    selectableLocationsVO2 = new SelectableVO(true, 2, "Christchurch");
    selectableLocationsVO3 = new SelectableVO(true, 3, "Nelson");
    selectableLocationsVO4 = new SelectableVO(true, 4, "Rotorua");
    selectableLocationsVO5 = new SelectableVO(true, 5, "Queenstown"); 
    selectableLocationsVO6 = new SelectableVO(true, 6, "Wanaka"); 
    
    locationsDataGridProvider = new ArrayCollection(); 
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox1, colId:selectableLocationsVO1.id, colDescription: selectableLocationsVO1.description});
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox2, colId:selectableLocationsVO2.id, colDescription: selectableLocationsVO2.description});
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox3, colId:selectableLocationsVO3.id, colDescription: selectableLocationsVO3.description});
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox4, colId:selectableLocationsVO4.id, colDescription: selectableLocationsVO4.description});
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox5, colId:selectableLocationsVO5.id, colDescription: selectableLocationsVO5.description});
    locationsDataGridProvider.addItem({colSelectable:selectLocationCheckBox6, colId:selectableLocationsVO6.id, colDescription: selectableLocationsVO6.description});    
    locationsToIncludeGrid.dataProvider = locationsDataGridProvider;  
     
    totalPaxTextBox.text = "0";  
    totalDurationTextBox.text = "0";   
}

private function validateForm(evt:MouseEvent):void {
	if (new BaseProductSearchValidator(validatorArr).validateForm(evt)) {
	    search();
	}		
}

private function resetForm(evt:MouseEvent):void {	
	choiceOfIslandsComboBox.selectedIndex=-1; 	
	departingDate.selectedDate = null;
	returningDate.selectedDate = null;    
	itineraryOptionsGrid.dataProvider = new ArrayCollection();
	totalPaxTextBox.text = "0";  
    totalDurationTextBox.text = "0"; 
}

private function resetActivityChoices(evt:MouseEvent):void {
	//var x:CheckBox = this.activitiesDataGridProvider.getItemAt(0) as CheckBox;
	//x.selected=false;
	selectActivityCheckBox1.selected=false;
	selectActivityCheckBox2.selected=false;
    selectActivityCheckBox3.selected=false;
    selectActivityCheckBox4.selected=false;
    selectActivityCheckBox4.selected=false;
}

private function resetLocationChoices(evt:MouseEvent):void {
	selectLocationCheckBox1.selected=false;
	selectLocationCheckBox2.selected=false;
    selectLocationCheckBox3.selected=false;
    selectLocationCheckBox4.selected=false;
}

public function search():void
{
    //this.suggestedItinerarySearchVO = new SuggestedItinerarySearchVO();
   // this.accommodationSearchVO.id = new Number(idText.text);
    //this.accommodationSearchVO.name = nameText.text;
    //accommodationSearchController.search(accommodationSearchVO);
    
    choiceOfItineraryItems = new ArrayCollection();
    choiceOfItineraryItems.addItem("25 day tour, including 5 nights at Rotorua, flight to Wanaka spending 8 nights at the Scenic circles hotel. Followed by 9 nights at Queenstown enjoying all that Queenstown offers");
    choiceOfItineraryItems.addItem("28 day tour, including Bay of Islands(4 nights), Auckland (2 nights), Rotorua(6 nights), Wellington (2 nights), Picton (1 night), Christchurch (3 nights), Wanaka (5 nights), Queenstown (4 nights) - back to Auckland");
    
    itineraryOptionsGrid.dataProvider = choiceOfItineraryItems; 
}

private function updateTotalDuration(evt:DropdownEvent):void {		
	if (returningDate.selectedDate != null && departingDate.selectedDate != null) {
		totalDurationTextBox.text = (returningDate.selectedDate.date - departingDate.selectedDate.date).toString();
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
