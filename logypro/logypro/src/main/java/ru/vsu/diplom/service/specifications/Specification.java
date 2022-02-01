package ru.vsu.diplom.service.specifications;

@FunctionalInterface
public interface Specification<T> {
    public boolean suit(T element);
}
