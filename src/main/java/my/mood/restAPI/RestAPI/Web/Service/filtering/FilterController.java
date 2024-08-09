package my.mood.restAPI.RestAPI.Web.Service.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	@GetMapping("filtering")
	public MappingJacksonValue filtering() {
		BeanOne beanOne = new BeanOne("type 1", "type 2", "type 3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanOne);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("type1", "type3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("BeanFilter", filter );
		
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;
	}
	
	@GetMapping("filtering-list")
	public  MappingJacksonValue filteringList() {
		List<BeanOne> asList = Arrays.asList( 
				new BeanOne("type 1", "type 2", "type 3"),
				new BeanOne("type 4", "type 5", "type 6"),
				new BeanOne("type 7", "type 8", "type 9"));
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(asList);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("type1", "type2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("BeanFilter", filter );
		
		jacksonValue.setFilters(filters );
		
		return jacksonValue;
	}
}
