package ar.edu.unlp.bbdd2.heracles.helper;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;

public class RefHelper {
	
//	public static class Func<T> implements Function<Ref<T>, T> {
//        public static Func<Object> INSTANCE = new Func<Object>();
//
//        @Override
//        public T apply(Ref<T> ref) {
//            return deref(ref);
//        }
//    }
//
//    public static <T> T deref(Ref<T> ref) {
//        return ref == null ? null : ref.get();
//    }
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static <T> List<T> deref(List<Ref<T>> reflist) {
//        return Lists.transform(reflist, (Func)Func.INSTANCE);
//    }
	
	public static <T> T deref (Ref<T> ref){
		if (ref != null){
			ref = (Ref<T>) ref.get();
		}
		return (T) ref;
	}
	
	public static <T> List<T> deref (List<Ref<T>> reflist){
		List<T> list = null;
		if (reflist != null){
			list = new ArrayList<T>();
			for (Ref<T> ref : reflist) {
				list.add(ref.get());
			}
		}
		return list;
	}
	
	public static <T> List<Ref<T>> ref (List<T> list){
		List<Ref<T>> reflist = null;
		if (list != null){
			reflist = new ArrayList<Ref<T>>();
			for (T deref : list) {
				reflist.add(Ref.create(deref));
			}
		}
		return reflist;
	}

}
