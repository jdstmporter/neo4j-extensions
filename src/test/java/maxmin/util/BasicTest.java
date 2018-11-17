package maxmin.util;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;

import org.neo4j.driver.v1.Value;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class BasicTest {
	
	public static double bound=1000.0;
	public static double error=1.0e-9;
	public static int nTests=1000;
	
	private String template;
	private BinaryOperator<Double> function;
	private Random rng;

	public BasicTest(String template_,BinaryOperator<Double> function_) {
		template=template_;
		function=function_;
		rng=new Random(System.currentTimeMillis());
	}
	
	public static boolean equal(double x,double y) {
		double diff=Math.abs(x-y);
		return diff<error*bound;
	}
	
	public boolean dieThrow() {
		return rng.nextDouble() > 0.5;
	}
	
	protected boolean singleTest(Session session) throws Throwable {
		List<Double> vals=Randomise.makeSymmetricList(2, bound);
		Double a=vals.get(0);
		Double b=vals.get(1);
		String command=String.format(template,a,b);
		Value result = session.run(command).single().get("result");
		double test=result.asDouble();
		double pred=function.apply(a, b);
		return equal(test,pred);
	}
	
	public boolean allTests(Neo4jRule neo4j) throws Throwable {
		try( Driver driver = GraphDatabase.driver(neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            Session session = driver.session();
            
            int success=0;
            int executed=0;
            
            for(int i=0;i<nTests;i++) {
            	try {
            		if(singleTest(session)) success+=1;
            		executed+=1;
            	}
            	finally {}
            }
            System.out.println(String.format("SUCCESS = %d EXECUTED = %d",success,executed));
            return success==nTests && executed==nTests;
        }	
	}
}
