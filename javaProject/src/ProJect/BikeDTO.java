package ProJect;

public class BikeDTO {
	private String num, branch, prname, company, whdate;
	private int money, amount, tot;
	//getter, setter, toString, 생성자(기본, 매개변수)
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPrname() {
		return prname;
	}
	public void setPrname(String prname) {
		this.prname = prname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWhdate() {
		return whdate;
	}
	public void setWhdate(String whdate) {
		this.whdate = whdate;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	@Override
	public String toString() {
		return "BikeDTO [num=" + num + ", branch=" + branch + ", prname=" + prname + ", company=" + company
				+ ", whdate=" + whdate + ", money=" + money + ", amount=" + amount + ", tot=" + tot + "]";
	}
	public BikeDTO(String num, String branch, String prname, String company, String whdate, int money, int amount) {
		this.num = num;
		this.branch = branch;
		this.prname = prname;
		this.company = company;
		this.whdate = whdate;
		this.money = money;
		this.amount = amount;
		tot=money*amount;
	}
	public BikeDTO() {
		
	}
}
