package ru.vsu.diplom.service.specification;

@FunctionalInterface
public interface Specification<T> {
    public boolean suit(T element);
}
