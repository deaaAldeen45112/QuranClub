package net.jordan.quran_club.datasource.remote;

public interface CommonApiServiceMethod<T> {


   T insert();
   T delete();
   T getList();
   T update();


}
