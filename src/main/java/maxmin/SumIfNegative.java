package maxmin;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserAggregationFunction;
import org.neo4j.procedure.UserAggregationResult;
import org.neo4j.procedure.UserAggregationUpdate;

public class SumIfNegative {
	@UserAggregationFunction
	@Description("maxmin.sumIfNeg(number) - sum of negative in list")
	public SumIfNegativeAggregator sumIfNeg() {
		return new SumIfNegativeAggregator();
	}
	
	public static class SumIfNegativeAggregator {
		private double sum = 0.0;
		
		@UserAggregationUpdate
		public void doSum( @Name("number") Double value) {
			if(value!=null && value<0) sum+=value;
		}
		
		@UserAggregationResult
		public Double result() {
			return sum;
		}
		
	}

}