package io.github.suhasark;

import java.util.Set;
import java.util.HashSet;

public class Variable {
    public String name;
    public Set<Integer> domain;

    public Variable(String name, Set<Integer> domain) {
        this.name = name;
        this.domain = new HashSet<>(domain);
    }
}
