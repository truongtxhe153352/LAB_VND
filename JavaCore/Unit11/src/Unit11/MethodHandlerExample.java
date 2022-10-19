package Unit11;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandlerExample {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(TotalCalculator.class, "getTotal",
                MethodType.methodType(long.class, short.class));
        TotalCalculator obj = new TotalCalculator();
        System.out.println("Value is : " + mh.invoke(obj, (short) 23));

        mh = lookup.findSetter(obj.getClass(), "TOTAL", int.class);
        mh.invoke(obj, 15);
        mh = lookup.findVirtual(obj.getClass(), "getTotal", MethodType.methodType(long.class, short.class));
        System.out.println("value is : " + mh.invoke(obj, (short) 23));

        mh = lookup.findVirtual(obj.getClass(), "getTotal", MethodType.methodType(long.class, short.class));
        System.out.println(mh.invoke(obj, (short)23));

        mh = MethodHandles.insertArguments(mh, 1, (short)10);
        System.out.printf("total = %d", mh.invoke(obj));
    }
}
