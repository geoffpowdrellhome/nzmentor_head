package constants
{
	import components.map.MarkerData;
	
	import mx.collections.ArrayCollection;
	
	public class GoogleConstants{
		public static const KEY:String = "blah";
		public static const DEFAULTMARKERS:ArrayCollection = new ArrayCollection([{markerData:new MarkerData(-41.2833,173.2833,
				"Nelson",5,"assets/images/locations/NelsonScenic.JPG", "The abel tasman national park is well known for it's wildlife safaris")},
														  {markerData:new MarkerData(-45.04278,168.64528,
			    "Queenstown",5, "assets/images/locations/Queenstown2.jpg", "Queenstown is best known for it's beautiful winter facilities such as skiing, skydiving and scenic walks. Cadrona skiifield is regarded as world class....")},
														  {markerData:new MarkerData(-44.7,169.15, 
				"Wanaka",6, "assets/images/mm_20_green.png",  "The Mt Aspiring National Park is world renowed for its beauty" )},
														  {markerData:new MarkerData(-43.5,172.6,
				"Christchurch",4, "assets/images/locations/Christchurch.JPG", "Christchurch is the largest city in the South Island and famous for it's churches. Mt Hutt is the nearest skifield only 1 hour away, and is very popular with South Island skiiers" )},
														  {markerData:new MarkerData(-45.89,170.5,
				"Dunedin",5, "assets/images/locations/Dunedin-at-Sunset.JPG",  "Dunedin is famous for it's scottish heritage and many of the early settlers in the 19th century started their lives here. It is the university city of NZ, rich in culture from the 19th century")},
														 ]);
												
														 
	}
}