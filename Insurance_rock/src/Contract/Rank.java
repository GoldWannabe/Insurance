package Contract;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public class Rank {
	
	private enum Material{rock, rood, gold, money};
	private float fireFacilities;
	private boolean height;
	private Material material;
	private int scale;
	private float surroundingFacilities;

	public Rank(){

	}

	public void finalize() throws Throwable {

	}

	public float getFireFacilities() {
		return fireFacilities;
	}

	public void setFireFacilities(float fireFacilities) {
		this.fireFacilities = fireFacilities;
	}

	public boolean isHeight() {
		return height;
	}

	public void setHeight(boolean height) {
		this.height = height;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public float getSurroundingFacilities() {
		return surroundingFacilities;
	}

	public void setSurroundingFacilities(float surroundingFacilities) {
		this.surroundingFacilities = surroundingFacilities;
	}
	
	public String getMaterial() {
		return this.material.toString();
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}//end Rank