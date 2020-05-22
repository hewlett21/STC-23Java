package ru.enikhov.lesson07;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * В классе создается пул потоков
 * по количеству элементов массива, по которым надо вычислить факториал
 */
public class AppFactorial {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = {100, 3, 10000, 157, 1, 2, 31, 1203};
        ExecutorService executorService = Executors.newFixedThreadPool(arr.length);
        Map<Integer, Future<BigInteger>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Factorial result = new Factorial(arr[i]);
            Future<BigInteger> future = executorService.submit(result);
            map.put(arr[i], future);
        }
        for (Map.Entry<Integer, Future<BigInteger>> f : map.entrySet()) {
            System.out.println("Факториал " + f.getKey() + ": " + f.getValue().get());
        }
        executorService.shutdown();
    }
}

/**
 * Класс для вычисляет факториала
 */
class CountFactorial {
    public BigInteger getFactorial(int value) throws InterruptedException {
        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= value; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}


class Factorial implements Callable<BigInteger> {
    private int val;
    private BigInteger factorial;

    public Factorial(int val) {
        this.val = val;
    }

    public BigInteger getFactorial() {
        return factorial;
    }

    @Override
    public BigInteger call() throws Exception {
        CountFactorial countFactorial = new CountFactorial();
        try {
            factorial = countFactorial.getFactorial(val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return factorial;
    }
}
