package entidades;

public class AtackItem extends Item{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int atack;//nº de pontos de ataque.
	private double range;//nº de km de alcance da arma de ataque
	private int id;
	
	
	/**
	 * Construtor por defeito
	 */
	public AtackItem(){
		super();
		this.atack = 0;
	}
	public AtackItem(String nome, String description, int cost, int atack){
		super(nome, description, cost);
		this.atack = atack;		
	}
	
	
	/**
	 * Getter do nº de pontos de ataque
	 * @return
	 */
	public int getAtack() {
		return atack;
	}
	/**
	 * setter do nº de pontos de ataque
	 * @param atack
	 */
	public void setAtack(int atack) {
		this.atack = atack;
	}
	@Override
	public String toString(){
		return getName() + " costs " + getCost() + " points and has " + atack + " points of atack\n" + getDescription();	
	}
	/**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}
	/**
	 * @param range the range to set
	 */
	public void setRange(double range) {
		this.range = range;
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
}
