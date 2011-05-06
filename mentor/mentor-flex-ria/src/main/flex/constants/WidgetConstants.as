package constants
{		
		
	import mx.collections.XMLListCollection;
	import mx.collections.ArrayCollection;
	import vo.common.IdValuePair;
	
	public class WidgetConstants
	{
		public static const ezyButtonHeight:Number = 21;
		public static const ezyButtonSmallHeight:Number = 17;
	         
        public static function getPaxSelectableData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="0" value="0" />') );
         	xmlListCollection.addItem( new XML('<node label="1" value="1" />') );
         	xmlListCollection.addItem( new XML('<node label="2" value="2" />') );
         	xmlListCollection.addItem( new XML('<node label="3" value="3" />') );
         	xmlListCollection.addItem( new XML('<node label="4" value="4" />') );
         	xmlListCollection.addItem( new XML('<node label="5" value="5" />') );
         	xmlListCollection.addItem( new XML('<node label="6" value="6" />') );
         	xmlListCollection.addItem( new XML('<node label="7" value="7" />') );
         	xmlListCollection.addItem( new XML('<node label="8" value="8" />') );
         	xmlListCollection.addItem( new XML('<node label="9" value="9" />') );
         	xmlListCollection.addItem( new XML('<node label="10" value="10" />') );
         	xmlListCollection.addItem( new XML('<node label="11" value="11" />') );
         	xmlListCollection.addItem( new XML('<node label="12" value="12" />') );
         	xmlListCollection.addItem( new XML('<node label="13" value="13" />') );
         	xmlListCollection.addItem( new XML('<node label="14" value="14" />') );
         	xmlListCollection.addItem( new XML('<node label="15" value="15" />') );
         	xmlListCollection.addItem( new XML('<node label="16" value="16" />') );
         	xmlListCollection.addItem( new XML('<node label="17" value="17" />') );
         	xmlListCollection.addItem( new XML('<node label="18" value="18" />') );
         	xmlListCollection.addItem( new XML('<node label="19" value="19" />') );
         	xmlListCollection.addItem( new XML('<node label="20" value="20" />') );
         	
         	return xmlListCollection;
        }
        
        public static function getChoiceOfIslandsData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="Both North and South" value="B" />') );
         	xmlListCollection.addItem( new XML('<node label="North only" value="N" />') );
         	xmlListCollection.addItem( new XML('<node label="South only" value="S" />') );	
         
         	return xmlListCollection;
        }
        
        public static function getAccommodationStarRatingData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="Five Star"  value="5" />') );
         	xmlListCollection.addItem( new XML('<node label="Four Star"  value="4" />') );
         	xmlListCollection.addItem( new XML('<node label="Three Star" value="3" />') );
         	xmlListCollection.addItem( new XML('<node label="Two Star"   value="2" />') );
         	xmlListCollection.addItem( new XML('<node label="One Star"   value="1" />') );	
         
         	return xmlListCollection;
        }
        
        public static function getAccommodationRoomTypeData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="Penthouse"  value="P" />') );
         	xmlListCollection.addItem( new XML('<node label="Sub Penthouse"  value="S" />') );
         	xmlListCollection.addItem( new XML('<node label="4 Bed / 2 Bath" value="4" />') );
         	xmlListCollection.addItem( new XML('<node label="3 Bed / 1 Bath"   value="3" />') );
         	xmlListCollection.addItem( new XML('<node label="2 Bed / 1 Bath"   value="2" />') );
         	xmlListCollection.addItem( new XML('<node label="1 Bed / 1 Bath"   value="1" />') );
         	xmlListCollection.addItem( new XML('<node label="Kitchenette"   value="K" />') );	
         
         	return xmlListCollection;
        }
        
        public static function getActivitiesSelectableData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="Bungy Jumping"  value="BJ" />') );
         	xmlListCollection.addItem( new XML('<node label="Fishing"  value="F" />') );
         	xmlListCollection.addItem( new XML('<node label="Skiing" value="S1" />') );
         	xmlListCollection.addItem( new XML('<node label="Sky Diving"   value="S2" />') );
         	xmlListCollection.addItem( new XML('<node label="Walks"   value="W" />') );
         	xmlListCollection.addItem( new XML('<node label="Wine Tours"   value="WT" />') );
         	         
         	return xmlListCollection;
        }
        
        
        public static function getLocationsSelectableData():XMLListCollection {
         	var xmlListCollection:XMLListCollection = new XMLListCollection();
         	
         	xmlListCollection.addItem( new XML('<node label="Auckland"  value="A" />') );         	
         	xmlListCollection.addItem( new XML('<node label="Christchurch" value="CH" />') );
         	xmlListCollection.addItem( new XML('<node label="Dunedin"   value="D1" />') );
         	xmlListCollection.addItem( new XML('<node label="Greymouth"   value="G1" />') );
         	xmlListCollection.addItem( new XML('<node label="Hastings"   value="H1" />') );
         	xmlListCollection.addItem( new XML('<node label="Hokitika"   value="H2" />') );
         	xmlListCollection.addItem( new XML('<node label="Masterton"   value="M1" />') );
         	xmlListCollection.addItem( new XML('<node label="Napier"   value="N1" />') );
         	xmlListCollection.addItem( new XML('<node label="Oamaru"   value="O1" />') );
         	xmlListCollection.addItem( new XML('<node label="Palmerston North"   value="P1" />') );
         	xmlListCollection.addItem( new XML('<node label="Queenstown"   value="Q1" />') );
         	xmlListCollection.addItem( new XML('<node label="Taupo"   value="T1" />') );
         	xmlListCollection.addItem( new XML('<node label="Tauranga"  value="T" />') );
         	xmlListCollection.addItem( new XML('<node label="Wanaka"  value="W1" />') );
         	xmlListCollection.addItem( new XML('<node label="Wellington"  value="W1" />') );
         	         
         	return xmlListCollection;
        }
        
        
        
        public static function getActivitiesForGridData():ArrayCollection {
         	var activityCollection:ArrayCollection = new ArrayCollection();
         	         	
         	activityCollection.addItem(new IdValuePair(1, "Bungy Jumping"));
         	activityCollection.addItem(new IdValuePair(1, "Fishing"));
         	activityCollection.addItem(new IdValuePair(1, "Skiing"));
         	activityCollection.addItem(new IdValuePair(1, "Sky Diving"));
         	activityCollection.addItem(new IdValuePair(1, "Walks"));
         	activityCollection.addItem(new IdValuePair(1, "Wine Tours"));
         	         	         
         	return activityCollection;
        }
        
        
        						


	}
}