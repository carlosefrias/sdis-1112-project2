package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

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
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;
import entidades.AtackItem;
import entidades.Avatar;
import entidades.DefenseItem;
import entidades.DummyAvatar;
import entidades.Item;
import entidades.MyLocation;

public class RestClass {

	//TODO: ir buscar o ip a um ficheiro de texto.
	private static String IpAndPort = "172.30.12.162:8080";
	//private static String IpAndPort = "172.30.11.171:8080";
	//(Funciona colocando o ip da máquina onde está a correr o servidor)
	
	private static String URL = "http://" + IpAndPort + "/AndroidGame/resources/";
	/**
	 * Função que efetua o pedido ao servidor devolve a resposta xml
	 * @param q pedido
	 */
	public static String callWebService(String pedido){  
		HttpClient httpclient = new DefaultHttpClient();  
		HttpGet request = new HttpGet(URL + pedido);  

		request.addHeader("deviceId", "id");  
		String result = "";
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
		return result;
	}	
	/**
	 * Função que faz parse de xml e devolve a string no interior da tag parametro e "subtag" subparametro
	 * @param parametro
	 * @param subparametro
	 * @param xmlString
	 * @return
	 */
	public static String getFromXML(String parametro, String subparametro, String xmlString){
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(parametro);
			Element element = (Element) nodes.item(0);

			NodeList name = element.getElementsByTagName(subparametro);
			return getCharacterDataFromElement((Element) name.item(0));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * Função que converte um objecto Element num objecto string
	 * @param e
	 * @return
	 */
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
	
	/**
	 * Função que retorna os items (ataque ou defesa) através do xml enviado como parâmetro
	 * @param xmlString xml
	 * @param tipoItem tipo de item (ataque ou defesa)
	 * @return
	 */
	public static ArrayList<Item> getItemsFromXML(String xmlString, String tipoItem){
		DocumentBuilder db;
		ArrayList<Item> array= new ArrayList<Item>();
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(tipoItem);
			for(int i = 0; i < nodes.getLength(); i++){
				Element element = (Element) nodes.item(i);
				
				NodeList descriptionNode = element.getElementsByTagName("description");
				String description = getCharacterDataFromElement((Element) descriptionNode.item(0));
				
				NodeList rangeValueNode = element.getElementsByTagName("rangeValue");
				Double rangeValue = Double.parseDouble(getCharacterDataFromElement((Element) rangeValueNode.item(0)));
				
				NodeList nameNode = element.getElementsByTagName("name");
				String name = getCharacterDataFromElement((Element) nameNode.item(0));				
				
				NodeList idNode = element.getElementsByTagName("id");
				int id = Integer.parseInt(getCharacterDataFromElement((Element) idNode.item(0)));
				
				if(tipoItem == "attackWeapon"){
					NodeList attackvalueNode = element.getElementsByTagName("attackValue");
					int attackvalue = Integer.parseInt(getCharacterDataFromElement((Element) attackvalueNode.item(0)));
			    	AtackItem item = new AtackItem(name, description, attackvalue, attackvalue);
			    	item.setRange(rangeValue);
			    	item.setId(id);
			    	array.add(item);
				} 
				if(tipoItem == "defenseWeapon"){
					NodeList defensevalueNode = element.getElementsByTagName("defenseValue");
					int defensevalue =Integer.parseInt(getCharacterDataFromElement((Element) defensevalueNode.item(0)));
			    	DefenseItem item = new DefenseItem(name, description, defensevalue, defensevalue);
			    	item.setRange(rangeValue);
			    	item.setId(id);
			    	array.add(item);	
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * Função que retorna do servidor os nomes dos tipos de avatares existentes na base de dados
	 * @param xmlString xml
	 * @return
	 */
	public static ArrayList<DummyAvatar> getAvatarsNamesFromXML(String xmlString){
		DocumentBuilder db;
		ArrayList<DummyAvatar> array = new ArrayList<DummyAvatar>();
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("avatar");
			for(int i = 0; i < nodes.getLength(); i++){
				Element element = (Element) nodes.item(i);
				
				NodeList nameNode = element.getElementsByTagName("name");
				String name = getCharacterDataFromElement((Element) nameNode.item(0));
				
				NodeList idNode = element.getElementsByTagName("id");
				int id = Integer.parseInt(getCharacterDataFromElement((Element) idNode.item(0)));
			
				DummyAvatar dummy = new DummyAvatar();
				dummy.setNome(name);
				dummy.setId(id);
				array.add(dummy);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * Função que devolve a lista de avatares existentes na base de dados
	 * @param xmlString
	 * @return
	 */
	public static ArrayList<Avatar> getAvatarFromXML(String xmlString){
		DocumentBuilder db;
		ArrayList<Avatar> array = new ArrayList<Avatar>();
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("userAvatar");
			for(int i = 0; i < nodes.getLength(); i++){
				Element element = (Element) nodes.item(i);
				
				NodeList userIdNode = element.getElementsByTagName("userId");
				int userId = Integer.parseInt(getCharacterDataFromElement((Element) userIdNode.item(0)));
				System.out.println("userID: "+userId);
				
				NodeList iddefenseweaponNode = element.getElementsByTagName("iddefenseweapon");
				int iddefenseweapon = Integer.parseInt(getCharacterDataFromElement((Element) iddefenseweaponNode.item(0)));
				System.out.println("iddefesa: " + iddefenseweapon);
				
				NodeList avatarIdNode = element.getElementsByTagName("avatarId");
				int avatarId = Integer.parseInt(getCharacterDataFromElement((Element) avatarIdNode.item(0)));
				System.out.println("type: " + avatarId);

				
				NodeList locationIdNode = element.getElementsByTagName("locationId");
				int locationId = Integer.parseInt(getCharacterDataFromElement((Element) locationIdNode.item(0)));
				System.out.println("locationId: " + locationId);
				
				Avatar avatar = new Avatar();
				avatar.setId(userId);
				avatar.setDefenseItem(getDefenseFromId(iddefenseweapon));
				avatar.setType(getAvatarNameFromID(avatarId));
				MyLocation local = getLocationFromID(locationId);
				avatar.setLatitude(local.getLatitude());
				avatar.setLongitude(local.getLongitude());
				avatar.rename(getUserNameFromId(userId));
				avatar.setPoints(getPointsFromId(userId));
				
				array.add(avatar);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * Função que devolve o item de defesa dado o seu id
	 * @param id
	 * @return
	 */
	private static DefenseItem getDefenseFromId(int id){
		DefenseItem item = new DefenseItem();
		String xmlString = callWebService("entities.defenseweapon/" + id + "?");
		DocumentBuilder db;
		try {			
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("defenseWeapon");
			Element element = (Element) nodes.item(0);
			
			NodeList descriptionNode = element.getElementsByTagName("description");
			String description = getCharacterDataFromElement((Element) descriptionNode.item(0));
			
			NodeList pointsNode = element.getElementsByTagName("defenseValue");
			int points = Integer.parseInt(getCharacterDataFromElement((Element) pointsNode.item(0)));
			
			NodeList nameNode = element.getElementsByTagName("name");
			String name = getCharacterDataFromElement((Element) nameNode.item(0));
			
			item.setDescription(description);
			item.setCost(points);
			item.setName(name);
			item.setDefense(points);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item;
	}
	private static MyLocation getLocationFromID(int id){
		String xmlString = callWebService("entities.location/" + id + "?");
		MyLocation location = new MyLocation(0, 0, id);
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("location");
			
			Element element = (Element) nodes.item(0);
			
			NodeList latitudeNode = element.getElementsByTagName("latitude");
			int latitude = Integer.parseInt(getCharacterDataFromElement((Element) latitudeNode.item(0)));
			
			NodeList longitudeNode = element.getElementsByTagName("longitude");
			int longitude = Integer.parseInt(getCharacterDataFromElement((Element) longitudeNode.item(0)));
			
			location.setLatitude(latitude);
			location.setLongitude(longitude);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return location;
	}
	/**
	 * Função que devolve o userName através do userid
	 * @param id
	 * @return
	 */
	private static String getUserNameFromId(int id){
		String xmlString = callWebService("entities.user/" + id + "?");
		String name = "";
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("user");
			
			Element element = (Element) nodes.item(0);
			
			NodeList userNameNode = element.getElementsByTagName("userName");
			name = getCharacterDataFromElement((Element) userNameNode.item(0));
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * Função que retorna o número de pontos dado o id do user
	 * @param id
	 * @return
	 */
	private static int getPointsFromId(int id){
		int points = 0;
		String xmlString = callWebService("entities.user/" + id + "?");
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("user");
			
			Element element = (Element) nodes.item(0);
			
			NodeList pointsNode = element.getElementsByTagName("points");
			points = Integer.parseInt(getCharacterDataFromElement((Element) pointsNode.item(0)));
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}

	public static String httpPost(String pedido, String[] paramName, String[] paramVal) throws Exception {
		java.net.URL url = new java.net.URL(URL + pedido);
		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		  conn.setRequestMethod("POST");
		  conn.setDoOutput(true);
		  conn.setDoInput(true);
		  conn.setUseCaches(false);
		  conn.setAllowUserInteraction(false);
		  conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		  
		  // Create the form content
		  OutputStream out = conn.getOutputStream();
		  Writer writer = new OutputStreamWriter(out, "UTF-8");
		  for (int i = 0; i < paramName.length; i++) {
		    writer.write(paramName[i]);
		    writer.write("=");
		    writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
		    writer.write("&");
		  }
		  writer.close();
		  System.out.println(" #"+out);
		  out.close();

		  if (conn.getResponseCode() != 200) {
		   // throw new IOException(conn.getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  conn.disconnect();
		  return sb.toString();
		}
	private static String getAvatarNameFromID(int avatarId){

		String name = "";
		String xmlString = callWebService("entities.avatar/"+avatarId+"?");
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("avatar");
			
			Element element = (Element) nodes.item(0);
			
			NodeList nameNode = element.getElementsByTagName("name");
			name = getCharacterDataFromElement((Element) nameNode.item(0));
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
		
	}

}
