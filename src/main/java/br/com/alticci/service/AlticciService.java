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
        alticciRecursive(r);
        return resultList;
    }

    public void alticciRecursive(Long a){
        if(a.intValue() <= 3) resultList++;
        else {
            alticciRecursive(a - 3);
            alticciRecursive(a - 2);
        }
    }

}
