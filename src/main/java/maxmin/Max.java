package maxmin;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;


public class Max 
{
    @UserFunction
    @Description("maxmin.max(value1,value2) - numerical maximum of the arguments.")
    public double max(@Name("value1") double value1, @Name("value2") double value2) {
    	return Math.max(value1,value2);
    }
}






