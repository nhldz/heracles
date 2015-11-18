package ar.edu.unlp.bbdd2.heracles.helper;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTransform<T> {

	/**
	 * Transforma List<T> en el json correspondiente
	 * 
	 * @param list
	 * @return
	 */
	public static <T> String listToJson(List<T> list) {
		DataTableObject<T> dataTableObject = new DataTableObject<T>();
		dataTableObject.setAaData(list);
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(list.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		return json;
	}

	/**
	 * Transforma object en el json correspondiente
	 * 
	 * @param object
	 * @return
	 */
	public static <T> String objectToJson(T object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(object);
	}

}
