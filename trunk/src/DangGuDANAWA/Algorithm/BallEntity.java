package DangGuDANAWA.Algorithm;


public class BallEntity {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float px;
	private float py;
	private float rx;
	private float ry;
	public Boolean VisiblePoint;
	public int index;
	public int color;
	public BallEntity() {
	}
	
	public float getRx() {
		return rx;
	}
	public void setRx(float rx) {
		this.rx = rx;
	}
	public float getRy() {
		return ry;
	}
	public void setRy(float ry) {
		this.ry = ry;
	}
	public float getPx() {
		return px;
	}
	public void setPx(float px) {
		this.px = px;
	}
	public float getPy() {
		return py;
	}
	public void setPy(float py) {
		this.py = py;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getVx() {
		return vx;
	}
	public void setVx(float vx) {
		this.vx = vx;
	}
	public float getVy() {
		return vy;
	}
	public void setVy(float vy) {
		this.vy = vy;
	}
}
