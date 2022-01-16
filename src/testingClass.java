import java.sql.*;
import java.time.*;
public class testingClass {
	public static void main(String args[]) {
		LocalDate now = LocalDate.now();
		System.out.println(now.getDayOfWeek());
	}
}

