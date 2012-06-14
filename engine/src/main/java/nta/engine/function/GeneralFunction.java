package nta.engine.function;

import com.google.gson.Gson;
import nta.catalog.Column;
import nta.datum.Datum;

import nta.engine.json.GsonCreator;
import nta.storage.Tuple;

/**
 * @author Hyunsik Choi
 */
public abstract class GeneralFunction<T extends Datum> extends Function<T> {
  public GeneralFunction(Column[] definedArgs) {
    super(definedArgs);
  }

  public abstract void eval(Tuple params);

  @Override
  public void merge(Tuple...parts) {
    eval(parts[parts.length - 1]);
  }

  @Override
  public abstract T terminate();

	public enum Type {
	  AGG,
	  GENERAL
	}

  public String toJSON() {
    Gson gson = GsonCreator.getInstance();
    return gson.toJson(this, GeneralFunction.class);
  }
}