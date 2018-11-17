package maxmin;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;


public class Min 
{
    @UserFunction
    @Description("maxmin.min(value1,value2) - numerical minimum of the arguments.")
    public double min(@Name("value1") double value1, @Name("value2") double value2) {
    	return Math.min(value1,value2);
    }
}
