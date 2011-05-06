package components.map
{
	import com.google.maps.overlays.Marker;
	
	public class MarkerData
	{
		public var lat:Number;
		public var lng:Number;
		public var name:String;
		public var maxZoom:Number;
		public var imgSourceUrl:String;
		public var bestKnownForInformation:String;
		public var touristInformation:String;		
		public var marker:Marker;
		
		public function MarkerData(_lat:Number,
														_lng:Number,
														_name:String, 
														_maxZoom:Number,
														_imgSourceUrl:String,
														_bestKnownForInformation:String)
		{			
			this.lat=_lat;
			this.lng=_lng;
			this.name=_name;
			this.maxZoom=_maxZoom;
			this.imgSourceUrl=_imgSourceUrl;
			this.bestKnownForInformation=_bestKnownForInformation;			
		}
		
	}
}