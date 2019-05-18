package rest;

import java.util.Comparator;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Resource controller that handles HTTP requests and serves Companies.
 * @author: Ivan Mykolenko
 * @email: vtuse@mail.ru
 */

@RestController
public class CompanyController {

	/**
	 * Receives JSONs of Companies in request entity body and returns them ordered
	 * by Company name in JSON format. Jackson serialiser will take care of
	 * converting to JSON.
	 * 
	 * @param json a string of companies in JSON format.
	 * @return ordered JSONs
	 */
	@RequestMapping(value = "/sortcompanies", method = RequestMethod.POST)
	public @ResponseBody List<Company> test(@RequestBody List<Company> json) {
		json.sort(companyComparator);
		return json;
	}

	/**
	 * Using Java Comparator interface to sort incoming JSONS
	 */
	private static Comparator<Company> companyComparator = new Comparator<Company>() {
		public int compare(Company c1, Company c2) {
			return c1.getName().compareTo(c2.getName());
		}
	};

}