package com.sdistp2.spycounterspy;

import java.util.ArrayList;
import java.util.List;

import rest.RestClass;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import entidades.Avatar;


public class ViewMap extends MapActivity implements OnClickListener, LocationListener{
	private TextView nomeAvatar;
	private Button back, changeMap, sair;
	public Avatar avatar;
	private MapView mapview;
	private boolean isSatellite = false;
	
	private int lat, longi;
    private LocationManager lm;
    private String towers;
	private final int defaultZoom = 18;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_map);
        nomeAvatar = (TextView) findViewById(R.id.NomeAvatarViewMap);
        nomeAvatar.setTextColor(Color.CYAN);
        back = (Button) findViewById(R.id.BackButtonOnViewMap);
        back.setOnClickListener(this);
        sair = (Button) findViewById(R.id.ViewMapSair);
        sair.setOnClickListener(this);
        changeMap = (Button) findViewById(R.id.ViewMapChangeStyle);
        changeMap.setOnClickListener(this);
        mapview = (MapView) findViewById(R.id.map);
        mapview.setBuiltInZoomControls(true);//para ter a opção zoom
        mapview.setSatellite(isSatellite);//para ter a visão de satélite
        
        
        //A carregar o avatar
        Bundle bundle = this.getIntent().getExtras();
        avatar = (Avatar) bundle.getSerializable("key");
        nomeAvatar.setText("" + avatar.toString());        
        
        //Obter a minha localização...
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        towers = lm.getBestProvider(crit, false);
        Location location = lm.getLastKnownLocation(towers);

   
        if(location != null){
        	//Se conseguiu obter uma localização...
        	lat = (int) (location.getLatitude() * 1E6);
        	longi = (int)(location.getLongitude() * 1E6);
        }
        else{
            //Se não conseguir obter a localização fazer...para já... O utilizador está aqui...
            lat = (int)(41.177458 * 1E6);
            longi = (int)(-8.595622 * 1E6);
        }
        
        GeoPoint ourlocation = new GeoPoint(lat, longi); 
        List<Overlay> mapOverlays = mapview.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.graymapmarker);
        ItemsOnMap itemizedOverlay = new ItemsOnMap(drawable, this);
        OverlayItem overlayitem1 = new OverlayItem(ourlocation, "", "You're here!");

        itemizedOverlay.addOverlay(overlayitem1, avatar);
        mapOverlays.add(itemizedOverlay);
        MapController mapController = mapview.getController();
        mapController.animateTo(ourlocation);
        mapController.setZoom(defaultZoom);
        
        //TODO: informar o servidor da minha posição
        avatar.setLatitude(ourlocation.getLatitudeE6());
        avatar.setLongitude(ourlocation.getLongitudeE6());

        String[] param = {"latitude", "longitude"}, valores = {"" + avatar.getLatitude(),"" + avatar.getLongitude()};
        try{
        	RestClass.httpPost("entities.location/new_local?", param, valores);        	
        }catch(Exception e){
        	e.printStackTrace();
        }
        int locationId = Integer.parseInt(RestClass.callWebService("entities.location/count?"));
        
        String[] param2 = {"id", "location_id"};
        String[] valores2 = {"" + avatar.getUserAvatarID(), "" + locationId};
        System.out.println("**ID** " + avatar.getUserAvatarID()+"  ***locationID*** "+locationId);
        try{
        	RestClass.httpPost("entities.useravatar/update_userAvatar?", param2, valores2);        	
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        //A obter a lista de todos os avatares
        String xmlString = RestClass.callWebService("entities.useravatar?");
        ArrayList<Avatar> avatares = RestClass.getAvatarFromXML(xmlString);
        System.out.println("** " + avatares);
        
        //A colocar todos avatares no mapa.
        for(int i = 0; i < avatares.size(); i++){
        	Avatar inimigo = avatares.get(i);
	        if(inimigo.getId() != avatar.getId()){
	        	GeoPoint enemyLocation = new GeoPoint(inimigo.getLatitude(), inimigo.getLongitude()); 
		        System.out.println("###### " +inimigo.getLatitude());
	        	OverlayItem overlayitem2 = new OverlayItem(enemyLocation, "" , "You're enemy is here!");
		        itemizedOverlay.addOverlay(overlayitem2, inimigo);
		        mapOverlays.add(itemizedOverlay);
	        }
        }
    }
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.BackButtonOnViewMap){
			//recuar para a activity (Equipar) enviando o avatar...
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), EquipActivity.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}else if(v.getId() == R.id.ViewMapChangeStyle){
            if (mapview.isSatellite())
            	mapview.setSatellite(false);
            else 
            	mapview.setSatellite(true);
		}else if(v.getId() == R.id.ViewMapSair){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Tem a certeza que pretende sair?")
		    .setCancelable(false)
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            //TODO: enviar para o servidor os dados do avatar e sair da aplicação, voltando à actividade inicial
					startActivity(new Intent(ViewMap.this.getApplicationContext(), LoginActivity.class));
		        }
		    })
		    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		             dialog.cancel();
		        }
		    });
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		lat = (int)(location.getLatitude() * 1E6);
		longi = (int)(location.getLongitude() * 1E6);
		//A actualizar o mapa...
		GeoPoint ourlocation = new GeoPoint(lat, longi); 
        List<Overlay> mapOverlays = mapview.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.graymapmarker);
        ItemsOnMap itemizedOverlay = new ItemsOnMap(drawable, this);
        OverlayItem overlayitem = new OverlayItem(ourlocation, "Hello " + avatar.getName() , "You're here!");

        itemizedOverlay.addOverlay(overlayitem, avatar);
        mapOverlays.add(itemizedOverlay);
        MapController mapController = mapview.getController();
        mapController.animateTo(ourlocation);
        mapController.setZoom(defaultZoom);
        avatar.setLatitude(ourlocation.getLatitudeE6());
        avatar.setLongitude(ourlocation.getLongitudeE6());
	}
	@Override
	public void onProviderDisabled(String provider) {

		
	}
	@Override
	public void onProviderEnabled(String provider) {
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
}

