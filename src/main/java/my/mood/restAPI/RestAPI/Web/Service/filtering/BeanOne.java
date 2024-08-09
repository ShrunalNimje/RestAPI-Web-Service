package my.mood.restAPI.RestAPI.Web.Service.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties({"type1", "type2"})
@JsonFilter("BeanFilter")
public class BeanOne {

	private String type1;
	private String type2;
	
	// @JsonIgnore
	private String type3;
	
	public BeanOne(String type1, String type2, String type3) {
		super();
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
	}

	public String getType1() {
		return type1;
	}

	public String getType2() {
		return type2;
	}

	public String getType3() {
		return type3;
	}

	@Override
	public String toString() {
		return "BeanOne [type1=" + type1 + ", type2=" + type2 + ", type3=" + type3 + "]";
	}
	
}
