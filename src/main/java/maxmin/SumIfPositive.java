package maxmin;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserAggregationFunction;
import org.neo4j.procedure.UserAggregationResult;
import org.neo4j.procedure.UserAggregationUpdate;

public class SumIfPositive {
	@UserAggregationFunction
	@Description("maxmin.sumIfPos(number) - sum of positives in list")
	public SumIfPositiveAggregator sumIfPos() {
		return new SumIfPositiveAggregator();
	}
	
	public static class SumIfPositiveAggregator {
		private double sum = 0.0;
		
		@UserAggregationUpdate
		public void doSum( @Name("number") Double value) {
			if(value!=null && value>0) sum+=value;
		}
		
		@UserAggregationResult
		public Double result() {
			return sum;
		}
		
	}

}
