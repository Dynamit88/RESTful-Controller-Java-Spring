package rest;

/*
 * Resource representation class.
 * @author: Ivan Mykolenko
 * @email: vtuse@mail.ru
 */

public class Company {
	private int custNo;
	private String name, country;

	public Company() {
		this.custNo = 0;
		this.name = "Def";
		this.country = "Def";
	}

	public Company(int custNo, String name, String country) {
		this.custNo = custNo;
		this.name = name;
		this.country = country;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "{\ncustNo: " + custNo + ",\nname: " + name + ",\ncountry: " + country + "\n}\n";
	}
}
