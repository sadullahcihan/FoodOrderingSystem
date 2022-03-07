import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Authorization {
	public static int userIdCounter = 1;

	public Customer register(Customer newUser, boolean isPremium) throws IOException {
		// according to phone number old users has been checked
		if (!checkExistingPhone(newUser.getPhone())) {
			newUser.setId(userIdCounter);
			String userDatabase = Integer.toString(newUser.getId()) + "%%%" + newUser.getName() + "%%%"
					+ newUser.getEmail() + "%%%" + newUser.getPassword() + "%%%" + newUser.getPhone() + "%%%"
					+ newUser.getAddress() + "%%%" + newUser.getBirthday();
			if (isPremium)
				userDatabase = userDatabase.concat("%%%P");
			else
				userDatabase = userDatabase.concat("%%%N");
			writeToFile("user_database.txt", userDatabase); // write to file
			newUser.display();
			System.out.println("Registered succesfully!");
			return newUser;
		}
		System.out.println("This number belongs to someone. Please try another number");
		return null;
	}

	@SuppressWarnings("resource")
	public Customer login(String givenEmailORPhone, String givenPassword) throws IOException {
		Customer u1 = null;
		File fileDir = new File("user_database.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		String str;
		in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		int i = 0;
		while ((str = in.readLine()) != null) {
			if (i == 0) { // passing databese first line
				i++;
				continue;
			}
			// email password control
			String id = str.split("%%%")[0];
			String name = str.split("%%%")[1];
			String email = str.split("%%%")[2];
			String pass = str.split("%%%")[3];
			String phoneNum = str.split("%%%")[4];
			String address = str.split("%%%")[5];
			String birthday = str.split("%%%")[6];
			String userType = str.split("%%%")[7];
			if ((givenEmailORPhone.equals(email) || givenEmailORPhone.equals(phoneNum)) && givenPassword.equals(pass)) {
				System.out.println("\nLogin successful \n\nWELCOME! " + str.split("%%%")[1]);
				if (userType.equals("N"))
					u1 = new User(name, email, pass, phoneNum, address, birthday);
				else if (userType.equals("P"))
					u1 = new PremiumUser(name, email, pass, phoneNum, address, birthday);
				u1.setId(Integer.parseInt(id));
				in.close();
				return u1;
			}
		}
		in.close();
		System.out.println("ERROR! Email and/or password are wrong!");
		return u1;
	}

	public static void writeToFile(String path, String userInfos) {
		try (FileWriter f = new FileWriter(path, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(userInfos);

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static boolean checkExistingPhone(String phoneNumber) throws IOException {
		File fileDir = new File("user_database.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		int lineamount = 0;
		while (in.readLine() != null) // Getting line amount
			lineamount++;
		userIdCounter = lineamount - 1;
		String str;
		in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		int i = 0;
		while ((str = in.readLine()) != null) {
			if (i == 0) { // passing databese first line
				i++;
				continue;
			}
			// phone number control
			str = str.split("%%%")[4];
			if (phoneNumber.equals(str)) {
				System.out.println(str);
				in.close();
				return true;
			}
		}
		in.close();
		return false;
	}
}