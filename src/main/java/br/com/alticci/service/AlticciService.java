package br.com.alticci.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class AlticciService {

    private static final List<String> FIXED_RESULTS = Arrays.asList("1","2","3");
    private Long resultList = 0L;

    public Long getValue(String n){
        assert n != null;
        Long result = Long.parseLong(n);
        if(result==0) return result;
        if(FIXED_RESULTS.stream().anyMatch(s -> s.equals(n))) return 1L;
        Long value = alticciDelegate(result);
        resultList = 0L;
        return value;
    }

    public Long alticciDelegate(Long r){
        alticciRecursiveA(r - 3);
        alticciRecursiveB(r - 2);
        return resultList;
    }

    public void alticciRecursiveA(Long a){
        if(a.intValue() <= 3) resultList++;
        else {
            alticciRecursiveA(a - 3);
            alticciRecursiveB(a - 2);
        }
    }

    public void alticciRecursiveB(Long b){
        if(b.intValue() <= 3) resultList++;
        else {
            alticciRecursiveA(b - 3);
            alticciRecursiveB(b - 2);
        }
    }

}
