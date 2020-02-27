package entity;

public interface Mappable<K> {
   <K> K getKey();
   String getName();
}