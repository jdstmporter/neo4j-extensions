package maxmin;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

public class Coalesce {
	@UserFunction
    @Description("maxmin.coalesce(value1,value2) - value1 ?? value2.")
    public Double coalesce(@Name("value1") Double value1, @Name("value2") Double value2) {
    	return (value1==null) ? value2 : value1;
    }
	
	@UserFunction
    @Description("maxmin.nullZ(value1) - value1 ?? 0.")
    public Double nullZ(@Name("value1") Double value1) {
    	return (value1==null) ? 0 : value1;
    }
}
