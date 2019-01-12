package entidades;

import java.io.Serializable;

public class Avatar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, type;
	private int id, points, latitude, longitude, userAvatarID;
	private AtackItem atackitem;
	private DefenseItem defenseitem;
	private DummyAvatar dummyAvatar;
	/**
	 * Construtor por defeito
	 */
	public Avatar(){
		this.name = "DefaultName";
	}
	/**
	 * Construtor passando por argumento o nome
	 * @param name
	 */
	public Avatar(String name){
		this.name = name;
	}
	/**
	 * Setter do nome do avatar
	 * @param newname
	 */
	public void rename(String newname){
		this.name = newname;
	}
	/**
	 * getter do nome
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Seter do AtackItem
	 * @param atackitem
	 */
	public void setAtackItem(AtackItem atackitem){
		this.atackitem = atackitem;
	}
	/**
	 * Getter do AtackItem
	 * @return
	 */
	public AtackItem getAtackItem(){
		return this.atackitem;
	}
	/**
	 * Setter do DefenseItem
	 * @param defenseitem
	 */
	public void setDefenseItem(DefenseItem defenseitem){
		this.defenseitem = defenseitem;
	}
	public DefenseItem getDefenseItem(){
		return this.defenseitem; 
	}
	/**
	 * Getter do número de pontos
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Setter do número de pontos
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	@Override
	public String toString(){
		return "" + name + " tem " + getPoints() + " pontos"; 		
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the longitude
	 */
	public int getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public int getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the dummyAvatar
	 */
	public DummyAvatar getDummyAvatar() {
		return dummyAvatar;
	}
	/**
	 * @param dummyAvatar the dummyAvatar to set
	 */
	public void setDummyAvatar(DummyAvatar dummyAvatar) {
		this.dummyAvatar = dummyAvatar;
	}
	/**
	 * @return the userAvatarID
	 */
	public int getUserAvatarID() {
		return userAvatarID;
	}
	/**
	 * @param userAvatarID the userAvatarID to set
	 */
	public void setUserAvatarID(int userAvatarID) {
		this.userAvatarID = userAvatarID;
	}
}
