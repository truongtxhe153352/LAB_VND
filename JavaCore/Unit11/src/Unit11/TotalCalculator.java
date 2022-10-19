package Unit11;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TotalCalculator {
    int TOTAL = 3;

    private int getTotal() {
        return TOTAL;
    }

    private int getTotal(int value) {
        return TOTAL + value;
    }

    @Test(34)
    public long getTotal(short value){
        return TOTAL + value;
    }

    @Test(100)
    public void print(int value){
        System.out.println(" gia tri them vao la " + value);
    }


}

class ReflectionTest {
    public static void explore(Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class clazz = obj.getClass();
        System.out.println(" clazz name: " + clazz.getName());

        Field field = clazz.getDeclaredField("TOTAL");
        field.setAccessible(true);

//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.setInt(obj, 23);
        System.out.println("modified value of the total field id " + field.get(obj));

        field.set(obj, 25);
        System.out.println("total value is " + field.get(obj));

        Method method = clazz.getDeclaredMethod("getTotal", new Class[]{int.class});
        method.setAccessible(true);
        System.out.println("method return value = " + method.invoke(obj, new Object[]{5}));


    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Object obj = new TotalCalculator();
        explore(obj);
    }
}
