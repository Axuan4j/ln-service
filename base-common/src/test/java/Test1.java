import com.wu.ln.constant.RoleBackEndConstant;
import com.wu.ln.util.RoleUtil;

public class Test1 {

    public static void main(String[] args) {
        int target = RoleBackEndConstant.OPERATOR.getRole() | RoleBackEndConstant.CUSTOMER.getRole() | RoleBackEndConstant.ADMIN.getRole();
        boolean hasRole = RoleUtil.hasPermission(target, RoleBackEndConstant.SUPER_ADMIN.getRole());
        System.out.println(hasRole);
    }
}
