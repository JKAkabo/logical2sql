import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NodeVisitor {
    public String visit(Node node) {
        String methodName = "visit" + node.getClass().getSimpleName();
        try {
            Method method = this.getClass().getMethod(methodName, node.getClass());
            return String.valueOf(method.invoke(this, node));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
