package tasks.t2;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinTest {

  private static SecureRandom random = new SecureRandom();

  public static void main(String[] args) {
    var workList = random.ints(100).boxed().toList();
    System.out.println(new DivideAndCompute(5, workList).compute());
  }
}

class DivideAndCompute extends RecursiveTask<Integer> {

  private final int threshold;
  private final List<Integer> workList;

  DivideAndCompute(int threshold, List<Integer> workList) {
    this.threshold = threshold;
    this.workList = workList;
  }

  @Override
  protected Integer compute() {
    if (workList.size() < threshold) {
      return computeResult();
    } else {
      var taskList = createTaskList();
      return ForkJoinTask.invokeAll(taskList)
          .stream()
          .map(ForkJoinTask::join)
          .reduce(0, Integer::sum);
    }
  }

  private List<DivideAndCompute> createTaskList() {
    return List.of(
        new DivideAndCompute(threshold, workList.subList(0, workList.size() / 2)),
        new DivideAndCompute(threshold, workList.subList(workList.size() / 2, workList.size()))
    );
  }

  private Integer computeResult() {
    return workList.stream().reduce(0, Integer::sum);
  }
}
