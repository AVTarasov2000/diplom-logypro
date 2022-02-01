package ru.vsu.diplom.service.specifier;

public interface Specifier<T> {
    public boolean specify(T element);
}
