package fr.umlv.javainside.labOne;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class TestBenchMark {
	
	@Param({ "10", "100", "1000", "100000", "1000000"})
	int max;

	@Benchmark
	public int loopSumThousandBench() {
		return Sums.loopSum(max);
	}
	
	@Benchmark
	public int StreamSumThousandBench() {
		return Sums.streamSum(max);
	}
	
	public static void main(String[] args) throws RunnerException {
	    Options opt = new OptionsBuilder()
	            .include(TestBenchMark.class.getSimpleName())
	            .forks(1)
	            .build();
	    new Runner(opt).run();
	}

}