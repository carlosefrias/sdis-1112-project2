package com.sdistp2.spycounterspy;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rest.RestClass;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import entidades.Avatar;

public class ViewEnemyAct extends Activity implements OnClickListener{
	private TextView avatarName, defenseName, Defensedescription, attackName, Attackdescription;
	private Button atack, back, sair;
	private ImageView avatarImage;
	private Avatar avatar, avatarEnemy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_enemy);

		avatarName = (TextView) findViewById(R.id.LabelEnemyName);
		defenseName = (TextView) findViewById(R.id.labelEnemyDefenseItemDescription);
		attackName = (TextView) findViewById(R.id.ViewEnemyAttackItem);
		Defensedescription = (TextView) findViewById(R.id.ViewEnemyDefenseDescription);
		Defensedescription.setTextColor(Color.CYAN);
		Attackdescription = (TextView) findViewById(R.id.ViewEnemyItemDescription);
		Attackdescription.setTextColor(Color.CYAN);
		avatarImage = (ImageView) findViewById(R.id.ViewEnemyImage);
		atack = (Button) findViewById(R.id.buttonAtackHim);
		back = (Button) findViewById(R.id.EnemyActBackButton);
		sair = (Button) findViewById(R.id.ViewEnemySair);
		atack.setOnClickListener(this);
		back.setOnClickListener(this);
		sair.setOnClickListener(this);

		//A carregar o avatar
		Bundle bundle = this.getIntent().getExtras();
		avatar = (Avatar) bundle.getSerializable("key");
		avatarEnemy = (Avatar) bundle.getSerializable("keyEnemy");
		avatarName.setTextColor(Color.CYAN);
		avatarName.setText(avatarEnemy.toString());
		if(avatar.getName().equals(avatarEnemy.getName())){//Se for o próprio.
			atack.setEnabled(false);
			defenseName.setText("Arma de Defesa: " + avatar.getDefenseItem().getName());
			Defensedescription.setText(avatar.getDefenseItem().getDescription() + "\n");
			attackName.setText("Arma de Ataque: " + avatar.getAtackItem().getName());
			Attackdescription.setText(avatar.getAtackItem().getDescription() + "\n");
		}
		else{
			defenseName.setText("Arma de Defesa: " + avatarEnemy.getDefenseItem().getName());
			Defensedescription.setText(avatarEnemy.getDefenseItem().getDescription() + "\n");
			attackName.setText("Distância ao Inimigo");
			Distancia dis = distance_km(avatar.getLatitude() / 1E6, avatar.getLongitude() / 1E6, avatarEnemy.getLatitude() / 1E6, avatarEnemy.getLongitude() / 1E6);
			Attackdescription.setText(dis.getUnidadeDistancia());
			boolean inRange = dis.getValorDistancia() <= avatar.getAtackItem().getRange();
			if(!inRange){
				atack.setEnabled(false);
				Defensedescription.append("\nO inimigo: " + avatarEnemy.getName() + " está fora do alcance da sua arma.");
			}
			else if(avatar.getPoints() <= 0){
				atack.setEnabled(false);
				Defensedescription.append("\nNão pode atacar, o seu nº de pontos não o permite.");
			}
			else if(avatarEnemy.getPoints() <= 0){
				atack.setEnabled(false);
				Defensedescription.append("\nNão pode atacar, o inimigo: " + avatarEnemy.getName() + " não tem pontos suficientes.");
			}
		}
		//A mostrar o avatar icon
		String iconSelected = avatarEnemy.getType();
		if(iconSelected.equals("Egipcio")){
			avatarImage.setImageResource(R.drawable.egipcian);	
		}
		else if(iconSelected.equals("Gaulês")){
			avatarImage.setImageResource(R.drawable.gaules);	
		}
		else if(iconSelected.equals("Viking")){
			avatarImage.setImageResource(R.drawable.viking);	
		}
		else{
			avatarImage.setImageResource(R.drawable.roman2);	
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonAtackHim){
			int pontosInimigo = avatarEnemy.getPoints();
			int pontosDefesaInimigo = avatarEnemy.getDefenseItem().getDefense();
			int pontosMe = avatar.getPoints();
			int pontosAtackMe = avatar.getAtackItem().getAtack();
			String pontos = "", ganhou = "";
			if(pontosAtackMe >= pontosDefesaInimigo){
				ganhou = avatar.getName();
				pontosMe += (pontosAtackMe - pontosDefesaInimigo);
				pontosInimigo -= (pontosAtackMe - pontosDefesaInimigo);
				pontos = avatar.getName() + ": " + pontosMe + " pontos."
						+ "\n" + avatarEnemy.getName() + ": " + pontosInimigo + " pontos.";
			}else{
				ganhou = avatarEnemy.getName();
				pontosInimigo += (pontosAtackMe - pontosDefesaInimigo);
				pontosMe -= (pontosAtackMe - pontosDefesaInimigo);
				pontos = avatar.getName() + ": " + pontosMe + " pontos."
						+ "\n" + avatarEnemy.getName() + ": " + pontosInimigo + " pontos.";
			}
			avatar.setPoints(pontosMe);
			avatarEnemy.setPoints(pontosInimigo);
			
			//Actualizar os pontos de cada um na base de dados.
			String[] paramName = {"id","points_1", "id2", "points_2"};
			String[] paramVal  = {"" + avatar.getId(), ""+avatar.getPoints(), ""+avatarEnemy.getId(), ""+avatarEnemy.getPoints()};
			try{
				RestClass.httpPost("entities.user/update_userPoints", paramName, paramVal);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			bundle.putSerializable("pontos", pontos);
			bundle.putSerializable("ganhou", ganhou);
			Intent newIntent = new Intent(this.getApplicationContext(), ViewAttackAct.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}
		else if(v.getId() == R.id.EnemyActBackButton){			
			//recuar para a activity anterior (Mostrar mapa) enviando o avatar...
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), ViewMap.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}
		else if(v.getId() == R.id.ViewEnemySair){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Tem a certeza que pretende sair?")
		    .setCancelable(false)
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            //TODO: enviar para o servidor os dados do avatar e sair da aplicação, voltando à actividade inicial
					startActivity(new Intent(ViewEnemyAct.this.getApplicationContext(), LoginActivity.class));
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
	/**
	 * Função que efetua o pedido ao servidor do google e recebe a resposta xml na variável result
	 * @param lat1 latitude do objecto 1
	 * @param long1 longitude do objecto 1
	 * @param lat2 latitude do objecto 2
	 * @param long2 longitude do objecto 2
	 * @return distância em km
	 */
	private Distancia distance_km(double lat1, double long1, double lat2, double long2){  
		String URL = "https://maps.googleapis.com/maps/api/distancematrix/xml?origins=" 
			+ lat1 + "," + long1 + "&destinations=" + lat2 + "," + long2 
			+ "&mode=walking&language=en-EN&sensor=false";
		String result = ""; 
		HttpClient httpclient = new DefaultHttpClient();  
		HttpGet request = new HttpGet(URL);  
		ResponseHandler<String> handler = new BasicResponseHandler();  
		try {  
			result = httpclient.execute(request, handler);  
		} catch (ClientProtocolException e) 
		{  
			e.printStackTrace();  
		} catch (IOException e) 
		{  
			e.printStackTrace();  
		}  
		httpclient.getConnectionManager().shutdown();  
		Log.i("", result);
		
		Distancia d = new Distancia();
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("distance");
			
			Element element = (Element) nodes.item(0);
			
			NodeList valueNode = element.getElementsByTagName("value");
			String value = RestClass.getCharacterDataFromElement((Element) valueNode.item(0));
			
			NodeList textNode = element.getElementsByTagName("text");
			String text = RestClass.getCharacterDataFromElement((Element) textNode.item(0));
			
			d.setUnidadeDistancia(text);
			int distEmMetros = Integer.parseInt(value);
			d.setValorDistancia(distEmMetros/1000.0);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	private class Distancia{
		private double valorDistancia;
		private String ValorComUnidadeDistancia;
		public Distancia(){
			
		}
		/**
		 * @return the valorDistancia
		 */
		public double getValorDistancia() {
			return valorDistancia;
		}
		/**
		 * @param valorDistancia the valorDistancia to set
		 */
		public void setValorDistancia(double valorDistancia) {
			this.valorDistancia = valorDistancia;
		}
		/**
		 * @return the unidadeDistancia
		 */
		public String getUnidadeDistancia() {
			return ValorComUnidadeDistancia;
		}
		/**
		 * @param unidadeDistancia the unidadeDistancia to set
		 */
		public void setUnidadeDistancia(String unidadeDistancia) {
			this.ValorComUnidadeDistancia = unidadeDistancia;
		}
		
	}
}
