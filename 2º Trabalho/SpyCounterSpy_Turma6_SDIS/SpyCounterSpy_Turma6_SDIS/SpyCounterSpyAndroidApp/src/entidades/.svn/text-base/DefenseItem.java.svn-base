package entidades;

public class DefenseItem extends Item{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int defense ;//nº de pontos de defesa.
	private double range; //alcance da arma de defesa.
	private int id;
	/**
	 * Construtor por defeito
	 */
	public DefenseItem(){
		super();
		this.setDefense(0);
	}
	/**
	 * Construtor com mais argumentos
	 * @param nome
	 * @param description
	 * @param cost
	 * @param defense
	 */
	public DefenseItem(String nome, String description, int cost, int defense){
		super(nome, description, cost);
		this.setDefense(defense);		
	}	
	
	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}
	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}
	@Override
	public String toString(){
		return getName() + " costs " + getCost() + " points and has " + defense + " points of defense\n" + getDescription();	
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
